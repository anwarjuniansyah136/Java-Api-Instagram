package com.instagram.instagrambe.service.message;

import java.util.List;

import com.instagram.instagrambe.dto.message.MessageResponseDto;

public interface MessageService {
    public String create(String userId,String text);
    public String edit(String commentId,String text);
    public String delete(String commentId);
    public List<MessageResponseDto> get();
}
