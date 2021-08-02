package com.forgerock.codingexercise.sample.service;

public interface PublisherService {
    
       /**
     * Publish message to redis
     * @param message Message object
     * @return
     */
    String pubMsg(String channel,String message);
}
