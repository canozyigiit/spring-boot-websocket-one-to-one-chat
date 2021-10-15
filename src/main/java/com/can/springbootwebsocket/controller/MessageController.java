package com.can.springbootwebsocket.controller;


import com.can.springbootwebsocket.model.Message;
import com.can.springbootwebsocket.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) {
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

        }
    }
}
