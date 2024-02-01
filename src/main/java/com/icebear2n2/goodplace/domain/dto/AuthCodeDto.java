package com.icebear2n2.goodplace.domain.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthCodeDto {
    private Long authCodeId;
    private Long userId; // User 엔티티의 ID
    private String phone;
    private String code;
    private Timestamp expirationTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp completedAt;

}
