package com.icebear2n2.goodplace.domain.dto;

import com.icebear2n2.goodplace.domain.entity.Comments;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsDto {
    private Long commentId;
    private Long userId; // User 엔티티의 ID
    private Long reviewId; // Reviews 엔티티의 ID
    private String commentContent;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CommentsDto(Comments comments) {
        this.commentId = comments.getCommentId();
        this.userId = comments.getUser().getUserId();
        this.reviewId = comments.getReviews().getReviewId();
        this.commentContent = comments.getCommentContent();
        this.createdAt = comments.getCreatedAt();
        this.updatedAt = comments.getUpdatedAt();
    }
}
