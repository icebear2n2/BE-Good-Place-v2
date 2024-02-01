package com.icebear2n2.goodplace.reviews.controller;

import com.icebear2n2.goodplace.domain.dto.ReviewsDto;
import com.icebear2n2.goodplace.domain.request.ReviewRequest;
import com.icebear2n2.goodplace.domain.request.UpdateReviewRequest;
import com.icebear2n2.goodplace.reviews.service.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    // TODO: 2/1/24 Controller Create

    @PostMapping
    public ReviewsDto createReviews(@RequestBody ReviewRequest reviewRequest) {
        return reviewsService.createReviews(reviewRequest);
    }

    @GetMapping("/all/{userId}")
    public Page<ReviewsDto> getAll(@PathVariable Long userId,
                                   @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                   @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest pageRequest = PageRequest.of(size, page);
        return reviewsService.getAllByUser(userId, pageRequest);
    }

    @PutMapping("{reviewsId}")
    public ReviewsDto updatedReviews(@PathVariable Long reviewsId, @RequestBody UpdateReviewRequest updateReviewRequest) {
        return reviewsService.updatedReviews(reviewsId, updateReviewRequest);
    }

    @DeleteMapping("/{reviewsId}")
    public void deleteReviews(@PathVariable Long reviewsId) {
        reviewsService.deleteReviews(reviewsId);
    }
}
