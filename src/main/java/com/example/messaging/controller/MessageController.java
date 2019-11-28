package com.example.messaging.controller;


import com.example.messaging.entity.Message;
import com.example.messaging.repo.MessageRepo;


import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private MessageRepo messageRepo;

    @Autowired
    private SimpMessageSendingOperations simpMessagingtemp;

    @Autowired
    MessageController (MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    Iterable<Message> getAllMessages() {
        
            Iterable<Message> allMessages = this.messageRepo.findAll();
            return allMessages;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Message sendMessage(@RequestBody String textMessage) {
            Message message = new Message();
            message.setTextMessage(textMessage);
            Message saveMsg = this.messageRepo.save(message);
            this.simpMessagingtemp.convertAndSend("/message/recieve", saveMsg.getTextMessage());
            return saveMsg;
    }






}