package com.instagram.instagrambe.service.story;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instagram.instagrambe.dto.story.StoryResponseDto;
import com.instagram.instagrambe.entity.Story;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.StoryRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class StoryServiceImpl implements StoryService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    StoryRepository storyRepository;

    @Override
    public String createStory(String caption,MultipartFile file) throws IOException, SQLException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        Story story = new Story();
        story.setUser(user);
        story.setCaption(caption);
        story.setMediaUrl(new SerialBlob(file.getBytes()));
        story.setStoryDate(convertToDate(LocalDateTime.now()));
        storyRepository.save(story);
        return "success";
    }
    
    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String deleteStory(String storyId) {
        Story story = storyRepository.getReferenceById(storyId);
        storyRepository.delete(story);
        return "success";
    }

    @Override
    public List<StoryResponseDto> getStoryByUser() throws IOException, SQLException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        List<Story> stories = storyRepository.findByUser(user);

        return stories.stream().map(this :: toStoryResponseDto).collect(Collectors.toList());
    }

    private StoryResponseDto toStoryResponseDto(Story story){
        try {
            return StoryResponseDto.builder()
                .storyId(story.getStoryId())
                .Username(story.getUser().getUsername())
                .image(blobToString(story.getMediaUrl()))
                .caption(story.getCaption())
                .storyDate(toString(story.getStoryDate()))
                .viewsCount(0)
                .expiresDate(null)
                .build();
        } catch (IOException | SQLException e) {
            throw new DataRetrievalFailureException("Failed to Convert Blob to String");
        }
    }

    private String toString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String toDate = formatter.format(date);
        return toDate;
    }

    private String blobToString(Blob blob) throws IOException, SQLException{
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        String image = Base64.getEncoder().encodeToString(bytes);
        return image;
    }
}
