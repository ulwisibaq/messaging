## Messaging API

### Send Message
```
POST
http://localhost:8085/send
Request Body : raw text / string
```

### Get All Messages
```
GET
http://localhost:8085/messages
```

### Realtime Message
```
Websocket API
host: http://localhost:8085/websocket
route for client : /message/recieve
```
test with websocket client : ``` src/main/resources/static/index.html```
