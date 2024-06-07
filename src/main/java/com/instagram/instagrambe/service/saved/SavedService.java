package com.instagram.instagrambe.service.saved;

import java.util.List;

import com.instagram.instagrambe.dto.saved.SavedResponseDto;

public interface SavedService {
    public String createSaved(String postId);
    public List<SavedResponseDto> getAll();
    public String deleteSaved(String postId);
}
