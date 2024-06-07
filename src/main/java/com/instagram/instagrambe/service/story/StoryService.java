package com.instagram.instagrambe.service.story;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.instagram.instagrambe.dto.story.StoryResponseDto;

public interface StoryService {
    public String createStory(String caption,MultipartFile file)throws IOException, SQLException;
    public String deleteStory(String storyId);
    public List<StoryResponseDto> getStoryByUser() throws IOException, SQLException;
}
