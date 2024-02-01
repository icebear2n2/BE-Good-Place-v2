package com.icebear2n2.goodplace.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Entity
@Table(name = "recommend")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Recommend {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recommendId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;
  @CreationTimestamp
  private Timestamp createdAt;

  

}
