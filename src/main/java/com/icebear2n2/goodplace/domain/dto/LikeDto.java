package com.icebear2n2.goodplace.domain.dto;

import com.icebear2n2.goodplace.domain.entity.Like;
import com.icebear2n2.goodplace.domain.entity.Recommend;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    private Long likeId;
    private Long userId; // User 엔티티의 ID
    private Long storeId; // Store 엔티티의 ID
    private Timestamp createdAt;

    public LikeDto(Like like) {
        this.likeId = like.getLikeId();
        this.userId = like.getUser().getUserId();
        this.storeId = like.getStore().getStoreId();
        this.createdAt = like.getCreatedAt();
    }
}
