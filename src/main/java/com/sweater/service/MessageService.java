package com.sweater.service;

import com.sweater.models.Message;
import com.sweater.models.User;
import com.sweater.models.dto.MessageDto;
import com.sweater.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    EntityManager entityManager;

    public Page<MessageDto> messageList(String filter,Pageable pageable, User user){
        if(filter!=null && !filter.isEmpty()){
            return messageRepository.findByTag(filter,pageable,user);
        }
        else{
            return messageRepository.findAll(pageable,user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser,User author) {
        return messageRepository.findByUser(pageable,currentUser,author);
    }
}
