# TopHashTag
Track top 10 hashtags based on input string

This a small and simple tool to keep track of hashtags(tweets/posts) and maintain most frequently used words(top 10 in here).
To run this clone/download the project. Install the maven dependencies and start the spring boot(tomcat) server or alternatively build the jar and deploy it on any server.
Once deployed, On UI hit **[http://localhost:5000/swagger-ui.html#!]** . This will open the Swagger UI where the exposed APIs can be easily accessed.

The Application exposes two APIs:

1. **/addText**: To read input String(as Tag Object) (POST)
2. **/getTrends**: To display top 10 used tags based on input string (GET)

The **POST** request uses regex matching to identify a **#** and its end inside a string.
The count is tracked in memory only (If you restart the server, you will loose them). This can be improved by asynchronously pushing the priority queue data to H2 db(local filesystem) or any other database.

# Approach

DataStructure Used: LinkedHashMap and PriorityQueue(min-heap)

Maintain a cache using LinkedHashMap. The LinkedHashMap will maintain top 50 recently used tweets with the value(Tag Object).
The Tag object has 2 fields : tag(String) and value(Integer). **tag** is the keyword being tracked and **value** is the number of times it has been used.

We maintain a priority queue with type Tag and custom comparator on it. This will act like min-heap and will maintain top 10 trends.
Another Hashset is maintained to effectively avoid the duplicate entries in the priority queue.

The LinkedHashMap values and PriorityQueue share same address or reference. So when a keyword come through and its updated in the map, it will also be updated in the priority queue because of shared reference.

If the value is more than the top of min heap then its checked in HashSet. If the keyword already exists, the queue is not altered. If not, then the top of the queue is polled and replaced by the new Tag Object.

[http://localhost:5000/swagger-ui.html#!]: http://localhost:5000/swagger-ui.html#!
