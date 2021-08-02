package com.forgerock.codingexercise.sample.config;

import com.forgerock.codingexercise.sample.job.MessageProcessor;
import com.forgerock.codingexercise.sample.listeners.AsyncMessageSubscriber;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig   {
    /**
     * Return a RedisTemplate Bean
     * @param redisConnectionFactory    If the cluster version is configured, the cluster version will be used; otherwise, the stand-alone version will be used
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<?, ?> template = new RedisTemplate<>();
        //Set key and value serialization mechanism
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.setConnectionFactory(redisConnectionFactory);  //Set up the connection factory of stand-alone or cluster version

        return template;
    }

    /**
     * System message adapter
     * @param receiver
     * @return
     */
    @Bean("logAdapter")
    public MessageListenerAdapter logAdapter(AsyncMessageSubscriber messageProcessor){
        //Specifies the method of callback receiving message in the class
        MessageListenerAdapter adapter = new MessageListenerAdapter(messageProcessor,"logMessage");
        //adapter.afterPropertiesSet();
        return adapter;
    }

    /**
     * SMS adapter
     * @param receiver
     * @return
     */
    @Bean("notificationAdapter")
    public MessageListenerAdapter notificationAdapter(AsyncMessageSubscriber processor){
        //Specifies the method of callback receiving message in the class
        MessageListenerAdapter adapter = new MessageListenerAdapter(processor,"notificationMessage");
        //adapter.afterPropertiesSet();
        return adapter;
    }

    /**
     * Building redis message listener container
     * @param connectionFactory
     * @param systemAdapter
     * @param smsAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter logAdapter, MessageListenerAdapter notificationAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //Specify different ways to listen to different channels
        container.addMessageListener(logAdapter, new PatternTopic("logMessage"));
        container.addMessageListener(notificationAdapter, new PatternTopic("alert"));
        return container;
    }
}
