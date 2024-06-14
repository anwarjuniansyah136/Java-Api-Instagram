package com.instagram.instagrambe.service.explore;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.dto.explore.ExploreResponseDto;
import com.instagram.instagrambe.entity.Explore;
import com.instagram.instagrambe.repository.ExploreRepository;

@Service
public class ExploreServiceImpl implements ExploreService{
    @Autowired
    ExploreRepository exploreRepository;

    @Override
    public List<ExploreResponseDto> getAll() {
        List<Explore> explores = exploreRepository.findAll();
        return explores.stream().map(this :: toExploreResponseDto).collect(Collectors.toList());
    }

    private ExploreResponseDto toExploreResponseDto(Explore explore){
        return ExploreResponseDto.builder()
            .id(explore.getExploreId())
            .post_id(explore.getPost().getPostId())
            .build();
    }

    @Override
    public ExploreResponseDto getById(String exploreId) {
        ExploreResponseDto responseDto = new ExploreResponseDto();
        Explore explore = exploreRepository.getReferenceById(exploreId);
        responseDto.setId(exploreId);
        responseDto.setPost_id(explore.getPost().getPostId());
        return responseDto;
    }
    
}
