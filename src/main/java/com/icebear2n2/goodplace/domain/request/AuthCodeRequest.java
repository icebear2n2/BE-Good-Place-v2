package com.icebear2n2.goodplace.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCodeRequest {
    private Long userId;
    private String phone;
}
