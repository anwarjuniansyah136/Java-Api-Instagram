package com.instagram.instagrambe.service.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.instagram.instagrambe.dto.post.PostResponseDto;

public interface PostService {
    public void postPhotoOrVideo(String caption,MultipartFile imagFile,MultipartFile vidUrl) throws IOException, SQLException;
    public String delete(String postId);
    public String update(String postId,String caption);
    public List<PostResponseDto> get();
}
