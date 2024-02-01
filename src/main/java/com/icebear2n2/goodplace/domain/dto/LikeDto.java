package com.icebear2n2.goodplace.domain.dto;

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
}
