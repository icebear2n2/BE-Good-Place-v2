package com.icebear2n2.goodplace.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserDataRequest {
    private Long age;
    private String gender;
}
