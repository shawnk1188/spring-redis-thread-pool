package com.forgerock.codingexercise.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService{

    
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public String pubMsg(String channel,String message) {
        String response = null;
        // TODO Auto-generated method stub
        try{
            redisTemplate.convertAndSend(channel, message);
            response = "Message to redis Queue channel:" + channel + "success!";
            System.out.println("Message to redis Queue channel:" + channel + "success!");
        }catch(Exception e){
            response="Message to redis Queue channel:" + channel + "Failed!";
            System.out.println(e.getMessage());
            System.out.println("Message to redis Queue channel:" + channel + "Failed!");

        }
        return response;
    }
    
}
