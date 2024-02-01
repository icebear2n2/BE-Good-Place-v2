package com.icebear2n2.goodplace.domain.dto;

import com.icebear2n2.goodplace.domain.entity.Reviews;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewsDto {
    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String reviewTitle;
    private String reviewContent;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReviewsDto(Reviews review) {
        this.reviewId = review.getReviewId();
        this.storeId = review.getStore().getStoreId();
        this.userId = review.getUser().getUserId();
        this.reviewTitle = review.getReviewTitle();
        this.reviewContent = review.getReviewContent();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
    }
}