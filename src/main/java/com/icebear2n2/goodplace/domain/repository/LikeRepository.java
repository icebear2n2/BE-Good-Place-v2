package com.icebear2n2.goodplace.domain.repository;

import com.icebear2n2.goodplace.domain.entity.Like;
import com.icebear2n2.goodplace.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Page<Like> findAllByUser(User user, Pageable pageable);
}