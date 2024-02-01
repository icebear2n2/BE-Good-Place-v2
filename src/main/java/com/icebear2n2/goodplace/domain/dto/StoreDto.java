package com.icebear2n2.goodplace.domain.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;
    private String address;
    private double coordinateX;
    private double coordinateY;
    private String foodType;
    private String myReview;
    private String storeName;
    private String storePhone;
    private String placeId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
