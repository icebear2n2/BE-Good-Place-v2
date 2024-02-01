package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Recommend;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRequest {
    private Long userId;
    private Long storeId;

    public Recommend toEntity(User user, Store store) {
        return Recommend.builder()
                .user(user)
                .store(store)
                .build();
    }
}
