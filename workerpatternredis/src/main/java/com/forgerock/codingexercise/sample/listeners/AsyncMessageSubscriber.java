package com.forgerock.codingexercise.sample.listeners;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.forgerock.codingexercise.sample.job.MessageProcessor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AsyncMessageSubscriber {
    private final MessageProcessor messageProcessor;

    private AtomicInteger counter = new AtomicInteger();
        //Message counter


    /**
     * Receive system messages and enable asynchronous monitoring
     * @param message
     */
    @Async
    public void logMessage(String message){
        int counter = this.counter.incrementAndGet();
        System.out.println("Received the" + counter + "Messages!! The channel is: logMessage，The message content is======: ");
        System.out.println("Message being sent:" + message);

        //TODO enables multithreading to send and process messages
        Map<String,String> result = messageProcessor.sendAndInsertMsg(message);
    }

    /**
     * Receive Alert messages and enable asynchronous monitoring
     * @param message
     */
    @Async
    public void notificationMessage(String message){
        int counter = this.counter.incrementAndGet();
        System.out.println("Received the" + counter + "Messages!! The channel is: notification，The message content is======: " + message);
        //TODO enable multi thread call sending
        Map<String,String> result = messageProcessor.sendAndInsertMsg(message);
    }
    
}
