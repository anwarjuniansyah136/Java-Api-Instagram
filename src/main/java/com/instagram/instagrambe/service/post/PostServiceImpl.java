package com.instagram.instagrambe.service.post;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.instagram.instagrambe.dto.post.PostResponseDto;
import com.instagram.instagrambe.entity.Post;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.PostRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void postPhotoOrVideo(String caption, MultipartFile imagFile,MultipartFile vidUrl) throws IOException, SQLException {
        String[] filename = Objects.requireNonNull(imagFile.getResource().getFilename()).split("\\.");
        String[] filenames = Objects.requireNonNull(imagFile.getResource().getFilename()).split("\\.");
        if (!filename[filename.length - 1].equalsIgnoreCase("jpg")
                && !filename[filename.length - 1].equalsIgnoreCase("jpeg")
                && !filename[filename.length - 1].equalsIgnoreCase("png")
                && !filenames[filenames.length - 1].equalsIgnoreCase("mp4")
                && !filenames[filenames.length - 1].equalsIgnoreCase("avi")
                && !filenames[filenames.length - 1].equalsIgnoreCase("mov")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported file type");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);
        System.out.println(user);
        Post post = new Post();
        post.setCaption(caption);
        post.setPostDate(convertToDate(LocalDateTime.now()));
        post.setCommentCount(0);
        post.setLikesCount(0);
        post.setUser(user);
        post.setImgUrl(new SerialBlob(imagFile.getBytes()));
        postRepository.save(post);
    }
    
    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String delete(String postId) {
        Post post = postRepository.getReferenceById(postId);
        postRepository.delete(post);
        return "Delete Your Post Succesfully";
    }

    @Override
    public String update(String postId, String caption) {
        Post post = postRepository.getReferenceById(postId);
        post.setCaption(caption);
        postRepository.save(post);
        return "Update Successfully";
    }

    @Override
    public List<PostResponseDto> get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);
        List<Post> posts = postRepository.findAllByUser(user);
        return posts.stream().map(this :: toPostResponseDto).collect(Collectors.toList());
    }

    private PostResponseDto toPostResponseDto(Post post){
        try {
            if(post.getVideoUrl() != null){
                Blob videoBlob = post.getVideoUrl();
                byte[] bytes = videoBlob.getBytes(1, (int) videoBlob.length());
                String videoUrl = Base64.getEncoder().encodeToString(bytes);
                SimpleDateFormat formatter = new SimpleDateFormat();
                String date = formatter.format(post.getPostDate());
                return PostResponseDto.builder()
                    .imgUrl(null)
                    .videoUrl(videoUrl)
                    .postDate(date)
                    .likeCount(post.getLikesCount())
                    .commentCount(post.getCommentCount())
                    .build();
            }
            else{
                Blob imageBlob = post.getImgUrl();
                byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
                String imageUrl = Base64.getEncoder().encodeToString(bytes);
                SimpleDateFormat formatter = new SimpleDateFormat();
                String date = formatter.format(post.getPostDate());
                return PostResponseDto.builder()
                    .imgUrl(imageUrl)
                    .videoUrl(null)
                    .postDate(date)
                    .likeCount(post.getLikesCount())
                    .commentCount(post.getCommentCount())
                    .build();
            }
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to Convert Blob to String");
        }
    }
}
