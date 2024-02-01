package com.icebear2n2.goodplace.recommend.controller;

import com.icebear2n2.goodplace.domain.dto.RecommendDto;
import com.icebear2n2.goodplace.domain.request.RecommendRequest;
import com.icebear2n2.goodplace.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {
    private final RecommendService recommendService;

    @PostMapping
    public RecommendDto connectionRecommend(@RequestBody RecommendRequest recommendRequest) {
        return recommendService.connectionRecommend(recommendRequest);
    }

    @GetMapping("/all/{userId}")
    public Page<RecommendDto> getAllByUser(@PathVariable Long userId,
                                           @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest pageRequest = PageRequest.of(size, page);
        return recommendService.getAllByUser(userId, pageRequest);
    }

    @DeleteMapping("/{recommendId}")
    public void deleteRecommend(@PathVariable Long recommendId) { recommendService.deleteRecommend(recommendId); }
}
