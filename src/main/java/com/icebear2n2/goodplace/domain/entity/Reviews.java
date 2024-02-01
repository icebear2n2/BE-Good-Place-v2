package com.icebear2n2.goodplace.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Reviews {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reviewId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  private String reviewTitle;
  private String reviewContent;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "reviews", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comments> comments;

  public void setStore(Store store) {
    this.store = store;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }
}
