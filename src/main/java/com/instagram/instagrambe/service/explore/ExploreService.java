package com.instagram.instagrambe.service.explore;

import java.util.List;

import com.instagram.instagrambe.dto.explore.ExploreResponseDto;

public interface ExploreService {
    public List<ExploreResponseDto> getAll();
    public ExploreResponseDto getById(String exploreId);
}
