package com.icebear2n2.goodplace.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "store")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reviews> reviews;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Like> likes;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Recommend> recommends;

  public void setAddress(String address) {
    this.address = address;
  }

  public void setCoordinateX(Double coordinateX) {
    this.coordinateX = coordinateX;
  }

  public void setCoordinateY(Double coordinateY) {
    this.coordinateY = coordinateY;
  }

  public void setFoodType(String foodType) {
    this.foodType = foodType;
  }

  public void setMyReview(String myReview) {
    this.myReview = myReview;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public void setStorePhone(String storePhone) {
    this.storePhone = storePhone;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }
}
