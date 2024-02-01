package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Reviews;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private Long storeId;
    private Long userId;
    private String reviewTitle;
    private String reviewContent;

    public Reviews toEntity(Store store, User user, String reviewTitle, String reviewContent) {
        return Reviews.builder()
                .store(store)
                .user(user)
                .reviewTitle(reviewTitle)
                .reviewContent(reviewContent)
                .build();
    }
}
