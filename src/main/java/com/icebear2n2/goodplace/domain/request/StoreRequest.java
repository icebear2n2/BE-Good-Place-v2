package com.icebear2n2.goodplace.domain.request;

import com.icebear2n2.goodplace.domain.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private String address;
    private Double coordinateX;
    private Double coordinateY;
    private String foodType;
    private String myReview;
    private String storeName;
    private String storePhone;
    private String placeId;



    public Store toEntity(String address, Double coordinateX, Double coordinateY, String foodType, String myReview, String storeName, String storePhone, String placeId) {
        return Store.builder()
                .address(address)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .foodType(foodType)
                .myReview(myReview)
                .storeName(storeName)
                .storePhone(storePhone)
                .placeId(placeId)
                .build();
    }
}
