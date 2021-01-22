# TopHashTag
Track top 10 hashtags based on input string

This a small and simple tool to keep track of hashtags(tweets/posts) and maintain most frequently used words(top 10 in here).
To run this clone/download the project. Install the maven dependencies and start the spring boot(tomcat) server or alternatively build the jar and deploy it on any server.
Once deployed, On UI hit **[http://localhost:5000/swagger-ui.html#!]** . This will open the Swagger UI where the exposed APIs can be easily accessed.

The Application exposes two APIs:

1. To Read String(as Tag Object) (POST)
2. To display top 10 used tags based on input string (GET)

The **POST** request uses regex matching to identify a **#** and its end inside a string.
The count is tracked in memory only (If you restart the server, you will loose them). This can be improved by async pushing the priority queue data to H2 db(local filesystem) or any other database.

# Approach

Maintain a LRU cache using LinkedHashSet and HashMap. The LinkedHashSet will maintain top 50 recently used tweets where as HashMap will contain the value(Tag Object).
The Tag object has 2 fields : tag(String) and value(Integer). **tag** is the keyword being tracked and **value** is the number of times it has been used.

We maintain a priority queue with type Tag and custom comparator on it. This will act like min-heap and will maintain top 10 trends.
Another Hashset is maintained to effectively avoid the duplicate entried in the priority queue.

The HashMap values and PriorityQueue share same address or reference. So when a keyword come through its updated in the map(which will also update in the priority queue because of shared reference)
and then checked for in the HashSet. If the keyword already exists, the queue is not altered. If not, then there is comparison between the top of the queue and the keyword value.
This might result in heapify if the keyword value is more than the minimum value on priority queue.

[http://localhost:5000/swagger-ui.html#!]: http://localhost:5000/swagger-ui.html#!
