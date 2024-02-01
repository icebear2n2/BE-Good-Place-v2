package com.icebear2n2.goodplace.domain.repository;

import com.icebear2n2.goodplace.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}