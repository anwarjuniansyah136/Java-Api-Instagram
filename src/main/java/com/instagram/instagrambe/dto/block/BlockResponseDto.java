package com.instagram.instagrambe.dto.block;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockResponseDto {
    private String blockId;
    private String usernameBlocker;
    private String usernameBlocked;
    private String blockDate;
}
