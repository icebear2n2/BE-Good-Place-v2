package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Role;
import com.icebear2n2.goodplace.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private Long age;
    private String gender;
    private String password;

    public User toEntity(String username, String email, Long age, String gender, String password) {
        return User.builder()
                .username(username)
                .email(email)
                .age(age)
                .gender(gender)
                .password(password)
                .role(Role.ROLE_USER)
                .build();
    }
}
