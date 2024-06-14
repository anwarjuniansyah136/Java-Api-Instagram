package com.instagram.instagrambe.service.block;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.instagram.instagrambe.dto.block.BlockResponseDto;
import com.instagram.instagrambe.entity.Block;
import com.instagram.instagrambe.entity.User;
import com.instagram.instagrambe.repository.BlockRepository;
import com.instagram.instagrambe.repository.UserRepository;

@Service
public class BlockServiceImpl implements BlockService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    BlockRepository blockRepository;

    @Override
    public String create(String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        Block block = new Block();
        block.setBlocker(user);
        User user2 = userRepository.getReferenceById(userId);
        block.setUser(user2);
        block.setBlockDate(convertToDate(LocalDateTime.now()));
        blockRepository.save(block);
        return "Success";
    }

    private Date convertToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String delete(String blockId) {
        Block block = blockRepository.getReferenceById(blockId);
        blockRepository.delete(block);
        return "Success";
    }

    @Override
    public List<BlockResponseDto> get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        List<Block> blocks = blockRepository.findByUser(user);
        return blocks.stream().map(this:: toBlockResponseDto).collect(Collectors.toList());
    }

    private BlockResponseDto toBlockResponseDto(Block block){
        return BlockResponseDto.builder()
            .blockId(block.getBlockId())
            .usernameBlocker(block.getBlocker().getUsername())
            .usernameBlocked(block.getUser().getUsername())
            .blockDate(convertToString(block.getBlockDate()))
            .build();
    }


    private String convertToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat();
        String dateToString = formatter.format(date);
        return dateToString;
    }
}
