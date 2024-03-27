package org.example.message.service;

import org.example.message.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDAO dao;

    @Autowired
    public MessageServiceImpl(MessageDAO dao) {
        this.dao = dao;
    }

}
