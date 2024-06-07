package com.instagram.instagrambe.service.message;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.dto.message.MessageResponseDto;
import com.instagram.instagrambe.entity.Message;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.MessageRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public String create(String userId,String text) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User sender = userRepository.findByUsername(username);
        User receive = userRepository.getReferenceById(userId);
        Message message = new Message();
        message.setSender(sender);
        message.setReceive(receive);
        message.setRead(false);
        message.setMessageText(text);
        message.setMessageDate(convertToDate(LocalDateTime.now()));
        messageRepository.save(message);
        return "success";
    }

    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String edit(String commentId,String text) {
        Message message = messageRepository.getReferenceById(commentId);
        message.setMessageText(text);
        messageRepository.save(message);
        return "success";
    }

    @Override
    public String delete(String commentId) {
        Message message = messageRepository.getReferenceById(commentId);
        messageRepository.delete(message);
        return "success";
    }

    @Override
    public List<MessageResponseDto> get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        List<Message> messages = messageRepository.findBySender(user);
        return messages.stream().map(this :: toMessageResponseDto).collect(Collectors.toList());
    }
    
    private MessageResponseDto toMessageResponseDto(Message message){
        return MessageResponseDto.builder()
            .messageId(message.getMessageId())
            .sender(message.getSender().getUsername())
            .receive(message.getReceive().getUsername())
            .text(message.getMessageText())
            .date(convertDateToString(message.getMessageDate()))
            .read(message.isRead())
            .build();
    }

    private String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String toString = formatter.format(date);
        return toString;
    }
}
