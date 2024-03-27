package org.example.message.controller;

import org.example.member.service.MemberService;
import org.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageControllerImpl implements MessageController{

    private final MessageService service;

    @Autowired
    public MessageControllerImpl(MessageService service) {
        this.service = service;
    }

}
