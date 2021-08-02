
Spring Data Redis Application Implementing Thread Pool to Recieve Message Queues.

Application Recieves Messages via rest api and pushes them to queue.


Build instructions (any 3rd party requirements and how to generally get them setup on either linux or mac) -- docker-compose or something similar is suggested

docker-compose up --build will start the application at port 8888

Usage instructions (i.e. samples to actually show how it works)

PostMan Requests:
Headers: content-type "application/json"
Method: POST

{
    "channelName":"logMessage",
    "logMessage":"sending in a log message no info log"
}

