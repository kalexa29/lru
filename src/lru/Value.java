package lru;

public class Value {
	public String value = "";
	public int timestamp = 0;
	
	@Override
	public String toString() {
		return "Value [value=" + value + ", timestamp=" + timestamp + "]";
	}
}
