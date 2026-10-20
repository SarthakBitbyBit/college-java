# assignment 27: java socket programming

## title and exercise descriptions
- title: develop a java application demonstrating socket programming for communication between a client and a server.
- exercise 1: client-server chat application supporting bidirectional asynchronous text messaging over tcp sockets with clear exit command (`/quit`) and clean resource closure.
- exercise 2: echo system where client sends text messages to the server on port 5002 and the server echoes back the exact message until termination.

## concepts demonstrated
- tcp socket communication using `ServerSocket` and `Socket`
- character stream buffering using `BufferedReader` and `PrintWriter`
- multithreading for concurrent sending and receiving in chat applications
- connection lifecycle management and clean termination without stack traces
- client-server protocol design and loopback testing on localhost

## source files
- `ChatServer.java`: multithreaded chat server listening on port 5001.
- `ChatClient.java`: multithreaded chat client connecting to server on port 5001.
- `EchoServer.java`: single-connection echo server listening on port 5002.
- `EchoClient.java`: interactive echo client transmitting text lines to port 5002.

## prerequisites and dependencies
- java development kit (jdk 25 or compatible)
- open local tcp ports 5001 and 5002

## compilation
```bash
javac ChatServer.java ChatClient.java EchoServer.java EchoClient.java
```

## execution commands

### 1. run exercise 1: bidirectional chat application
start the server in terminal 1:
```bash
java ChatServer
```
start the client in terminal 2:
```bash
java ChatClient
```

### 2. run exercise 2: echo system
start the server in terminal 1:
```bash
java EchoServer
```
start the client in terminal 2:
```bash
java EchoClient
```

## expected output examples

### exercise 1: chat server & client
server terminal:
```text
chat server starting on port 5001...
server waiting for client connection...
client connected from: /127.0.0.1:53702
client: hello from client
client: testing bidirectional chat
client has requested to end the chat
terminating server chat session
chat server stopped cleanly
```

client terminal:
```text
connecting to chat server at localhost:5001...
connected to chat server. type messages or /quit to exit
server: greetings from server
exiting chat application
connection terminated
chat client disconnected cleanly
```

### exercise 2: echo server & client
server terminal:
```text
echo server listening on port 5002...
waiting for incoming client connection...
client connected from: /127.0.0.1:39830
received: hello server
received: testing echo functionality
received: java socket programming
received: exit
client session ended cleanly
echo server shutdown complete
```

client terminal:
```text
connecting to echo server at localhost:5002...
connected to echo server. enter messages (type /quit or exit to finish):
server replied -> echo: hello server
server replied -> echo: testing echo functionality
server replied -> echo: java socket programming
server replied -> goodbye
echo client disconnected cleanly
```

## screenshots
- chat server: `screenshots/chat_server.png`
- chat client: `screenshots/chat_client.png`
- echo server: `screenshots/echo_server.png`
- echo client: `screenshots/echo_client.png`

## conclusion
socket programming enables network communication between processes across tcp connections. both the echo and bidirectional chat systems demonstrated reliable data transfer, thread coordination for concurrent i/o, and graceful session shutdown.
