package com.forgerock.codingexercise.sample.model;

import lombok.Data;

@Data
public class MessageResponse {
    
    private String httpCode;
    private String responseMessage;
}
