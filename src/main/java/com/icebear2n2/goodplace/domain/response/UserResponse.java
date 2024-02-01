package com.icebear2n2.goodplace.domain.response;

import com.icebear2n2.goodplace.domain.entity.Role;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private boolean success;
    private String message;
    private UserData data;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserData {
        private Long userId;
        private String username;
        private String email;
        private String nickname;
        private String phone;
        private String providerUserId;
        private String provider;
        private Long age;
        private String gender;
        private String grade;
        private String refreshToken;
        private Enum<Role> role;
        private String createdAt;
        private String updatedAt;
        private String deletedAt;

        public UserData(User user) {
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.phone = user.getPhone();
            this.providerUserId = user.getProviderUserId();
            this.provider = user.getProvider();
            this.age = user.getAge();
            this.gender = user.getGender();
            this.refreshToken = user.getRefreshToken();
            this.role = user.getRole();
            this.createdAt = user.getCreatedAt().toString();
            this.updatedAt = user.getUpdatedAt().toString();
            this.deletedAt = user.getDeletedAt() != null ? user.getDeletedAt().toString() : null;
        }
    }

    public static UserResponse success(User user) { return new UserResponse(true, "Success", new UserData(user)); }
    public static UserResponse failure(String message) { return new UserResponse(false, message, null); }
}
