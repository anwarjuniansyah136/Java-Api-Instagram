package com.instagram.instagrambe.dto.message;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {
    private String messageId;
    private String sender;
    private String receive;
    private String text;
    private String date;
    private boolean read;
}
