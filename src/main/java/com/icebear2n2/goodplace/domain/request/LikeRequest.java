package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Like;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequest {
    private Long userId;
    private Long storeId;

    public Like toEntity(User user, Store store) {
        return Like.builder()
                .user(user)
                .store(store)
                .build();
    }
}
