package com.icebear2n2.goodplace.domain.dto;

import com.icebear2n2.goodplace.domain.entity.Recommend;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendDto {
    private Long recommendId;
    private Long userId; // User 엔티티의 ID
    private Long storeId; // Store 엔티티의 ID
    private Timestamp createdAt;

    public RecommendDto(Recommend recommend) {
        this.recommendId = recommend.getRecommendId();
        this.userId = recommend.getUser().getUserId();
        this.storeId = recommend.getStore().getStoreId();
        this.createdAt = recommend.getCreatedAt();
    }
}
