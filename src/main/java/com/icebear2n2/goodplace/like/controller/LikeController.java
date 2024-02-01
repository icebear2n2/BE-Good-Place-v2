package com.icebear2n2.goodplace.like.controller;

import com.icebear2n2.goodplace.domain.dto.LikeDto;
import com.icebear2n2.goodplace.domain.request.LikeRequest;
import com.icebear2n2.goodplace.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public LikeDto connectionLike(@RequestBody LikeRequest likeRequest) {
        return likeService.connectionLike(likeRequest);
    }

    @GetMapping("/all/{userId}")
    public Page<LikeDto> getAllByUser(@PathVariable Long userId,
                                      @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest pageRequest = PageRequest.of(size, page);
        return likeService.getAllByUser(userId, pageRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) { likeService.deleteLike(likeId); }
}