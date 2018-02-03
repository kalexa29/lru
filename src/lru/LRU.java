package lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LRU {
	private static Map<String, Value> cache = new HashMap<String, Value>();
	private static int size;

	/**
	 * Get the key of the minimum timestamp in the cache
	 * @return
	 */
	public static String getMinTimestampKey() {
		int minTimestamp = Integer.MAX_VALUE;
		String minKey = null;
		for (String k : cache.keySet()) {
			if (cache.get(k).timestamp < minTimestamp) {
				minTimestamp = cache.get(k).timestamp;
				minKey = k;
			}
		}
		return minKey;
	}

	/**
	 * Update key with value and new timestamp. Removes oldest item in cache if there isn't enough space.
	 * Should respond with 'SET OK'
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		// check size of cache
		if (cache.size() == size) {
			cache.remove(getMinTimestampKey());
		}
		Value v = new Value();
		v.value = value;
		v.timestamp = (int) System.currentTimeMillis();
		cache.put(key, v);
		System.out.println("SET OK");
	}

	/**
	 * Get items with a line giving the key requested, your program should respond with the previously stored value, 
	 * for example, 'GOT foo'. 
	 * 
	 * If the key is not in the cache, it should reply with 'NOTFOUND'.
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String s = "NOTFOUND";
		if (cache.containsKey(key)) {
			Value v = new Value();
			v.value = cache.get(key).value;
			v.timestamp = (int) System.currentTimeMillis();
			cache.put(key, v);
			s = "GOT " + cache.get(key).value;
		}
		return s;
	}

	/** 
	 * Makes sure that the inputs match to a valid get or set method with the appropriate number of parameters
	 * 
	 * @param line
	 * @return
	 */
	public static boolean validInput(String line) {
		String arr[] = line.split(" ");
		String method = arr[0];
		boolean val = true;
		// check that there aren't too many inputs
		if (arr.length > 3 || arr.length == 1) {
			val = false;
		} else {
			String key = arr[1];
			// check that the method call exists...
			if (method.equalsIgnoreCase("get") && arr.length == 2) {
				// call get method
				System.out.println(get(key));
			} else if (method.equalsIgnoreCase("set") && arr.length == 3) {
				// call set method
				String value = arr[2];
				set(key, value);
			} else {
				val = false;
			}
		}
		return val;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (line.equalsIgnoreCase("exit")) { // stop scanning if the user inputs "EXIT"
				break;
			} else if (line.contains("SIZE") && cache.size() == 0) {
				size = Integer.parseInt(line.split(" ")[1]); // set size of cache
				System.out.println("SIZE OK");
			} else {
				if (!validInput(line)) {
					System.out.println("ERROR"); // print "ERROR" if it is an error
				}
			}
		}
		scanner.close();
		System.out.println("EXIT");
	}
}
