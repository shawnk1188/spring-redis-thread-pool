package com.forgerock.codingexercise.sample.controller;


import com.forgerock.codingexercise.sample.model.MessageRequest;
import com.forgerock.codingexercise.sample.service.PublisherService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
public class LogMessagesController {

  private final PublisherService publisherService;


    @PostMapping(value = "/push",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String push(@RequestBody MessageRequest request) {
      String response =publisherService.pubMsg(request.getChannelName(), request.getLogMessage());
      return response;
    }
  
   

}
