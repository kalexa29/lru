README

Definition:
- This is a coding exercise to implement a least recently used cache (lru). 

Requirements:
- Treat key / value pairs as strings delimited by a space. You're not required to accommodate keys or values that contain spaces.
- Inputs and outputs exist on their own line.
- The first input line should set the max number of items for the cache using the keyword SIZE. 
  The program should respond with 'SIZE OK'. This must only occur as the first operation.
- Set items with a line giving the key and value, separated by a space, your program should respond with 'SET OK'.
- Get items with a line giving the key requested, your program should respond with the previously stored value, for example, 'GOT foo'. 
  If the key is not in the cache, it should reply with 'NOTFOUND'.
- 'EXIT' should cause your program to exit.
- If the input is invalid, your program should respond with 'ERROR'

Sample Input
SIZE 3
GET bam
SET bam 7
GET bam
SET bam 33.3
GET bam
SET womp 5
GET womp
SET splat bug
SET zing bicycle
GET bam
GET womp
GET splat
GET splat zing
GET zing
EXIT

Expected Output
SIZE OK
NOTFOUND
SET OK
GOT 7
SET OK
GOT 33.3
SET OK
GOT 5
SET OK
SET OK
NOTFOUND
GOT 5
GOT bug
ERROR
GOT bicycle

Runtime Complexity:
- My algorithm is linear when the cache fills up to the size specified because the timestamp is stored in the value of the hash.

Known issues, limitations, assumptions:
- Multiple inputs could be done within the same second, which means that the algorithm would still check the whole cache and then choose the last minimum it encountered. 

Author: 
- Katelynn Alexander