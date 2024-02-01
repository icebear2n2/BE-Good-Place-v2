package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Comments;
import com.icebear2n2.goodplace.domain.entity.Reviews;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    private Long userId;
    private Long reviewsId;
    private String commentContent;

    public Comments toEntity(User user, Reviews reviews, String commentContent) {
        return Comments.builder()
                .user(user)
                .reviews(reviews)
                .commentContent(commentContent)
                .build();
    }
}
