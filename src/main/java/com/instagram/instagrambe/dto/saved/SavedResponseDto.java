package com.instagram.instagrambe.dto.saved;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedResponseDto {
    private String savedPostId;
    private String username;
    private String savedDate;
    private String caption;
}
