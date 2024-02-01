package com.icebear2n2.goodplace.domain.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String nickname;
    private Long age;
    private String gender;
    private String phone;
    private String providerUserId;
    private String provider;
    private String role;
    private String refreshToken;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
