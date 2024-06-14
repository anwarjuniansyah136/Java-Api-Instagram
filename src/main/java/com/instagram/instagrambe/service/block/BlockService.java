package com.instagram.instagrambe.service.block;

import java.util.List;

import com.instagram.instagrambe.dto.block.BlockResponseDto;

public interface BlockService {
    public String create(String userId);
    public String delete(String blockId);
    public List<BlockResponseDto> get();
}
