package com.forgerock.codingexercise.sample.model;

import lombok.Data;

@Data
public class MessageRequest {
    
    private String channelName;
    private String logMessage;
}
