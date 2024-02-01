package com.icebear2n2.goodplace.domain.repository;

import com.icebear2n2.goodplace.domain.entity.Recommend;
import com.icebear2n2.goodplace.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Page<Recommend> findAllByUser(User user, Pageable pageable);
}