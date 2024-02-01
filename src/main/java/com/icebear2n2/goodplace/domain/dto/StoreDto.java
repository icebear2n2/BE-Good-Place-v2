package com.icebear2n2.goodplace.domain.dto;

import com.icebear2n2.goodplace.domain.entity.Store;
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
    private Double coordinateX;
    private Double coordinateY;
    private String foodType;
    private String myReview;
    private String storeName;
    private String storePhone;
    private String placeId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public StoreDto(Store store) {
        this.storeId = store.getStoreId();
        this.address = store.getAddress();
        this.coordinateX = store.getCoordinateX();
        this.coordinateY = store.getCoordinateY();
        this.foodType = store.getFoodType();
        this.myReview = store.getMyReview();
        this.storeName = store.getStoreName();
        this.storePhone = store.getStorePhone();
        this.placeId = store.getPlaceId();
        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();
    }
}
