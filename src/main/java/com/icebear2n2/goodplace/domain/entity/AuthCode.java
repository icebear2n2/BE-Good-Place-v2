package com.icebear2n2.goodplace.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Entity
@Table(name = "auth_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuthCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authCodeId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  private String phone;
  private String code;
  private Timestamp expirationTime;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Timestamp completedAt;

  public void setUser(User user) {
    this.user = user;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setCompletedAt(Timestamp completedAt) {
    this.completedAt = completedAt;
  }
}
