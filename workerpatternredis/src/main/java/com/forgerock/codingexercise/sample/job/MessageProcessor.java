package com.forgerock.codingexercise.sample.job;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.forgerock.codingexercise.sample.model.MessageResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageProcessor {
    

    private final ThreadPoolTaskExecutor threadTaskExecutor;  //Inject thread pool

    /**
     * Multithreaded call to send message
     * @param String
     * @return
     */
    public Map<String,String> sendAndInsertMsg(String message) {

        Future<Map<String,String>> future = threadTaskExecutor.submit(new Callable<Map<String,String>>() {  //With return value
            @Override
            public Map<String,String> call() throws Exception {

                //1. Call a relatively time-consuming message sending method
                String code = sendMessage(message);
                //Adding Date to Message;
                MessageResponse resp = new MessageResponse();
                if ("200".equals(code)){    //Sent successfully
                    resp.setHttpCode("200");
                }else{  //fail in send
                    resp.setHttpCode("400");
                }

                //2. Insert messages
                insertMessage(resp);

                Map<String,String> result = new HashMap<>();
                result.put("code", "200");
                result.put("Message","Message sent successfully!");
                return result;
            }
        });

        Map<String,String> resultMap = new HashMap<>();   //Return results
        try{
            if (future.isDone()){   //When the thread scheduling ends, the result will be obtained
                resultMap = future.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;      //Message sending and storing results
    }

    /**
     * Call interface to send message
     * @param message
     * @return
     */
    private String sendMessage(String message) {
        try{
            if(message.contains("Failure")|| message.contains("error") || message.contains("400") || message.contains("500") || message.contains("down")){
                Thread.sleep(2000);     //Increase time-consuming operation and view multi-threaded effect
                System.out.println(Thread.currentThread().getName() + "Thread successfully sent message, message content:" + message);
                System.out.println(Thread.currentThread().getName() + "Thread successfully sent message to alert Queue, message content:" + message);

                //Send Email Notification TO App Owner
            }
            return "200";   //Send message result status code
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName() + "Thread failed to send message, message content:" + message);
            e.printStackTrace();
        }
        return "500";   //Send message result status code
    }

    /**
     * Save message to database
     * @param message
     * @return
     */
    private void insertMessage(MessageResponse message) {
        try{
            //TODO performs the insert message to data operation here
            System.out.println(Thread.currentThread().getName() + "Thread successfully inserted message into database, message content:" + message.toString());
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName() + "Thread insert message to database failed, message content:" + message.toString());
            e.printStackTrace();
        }
    }

}
