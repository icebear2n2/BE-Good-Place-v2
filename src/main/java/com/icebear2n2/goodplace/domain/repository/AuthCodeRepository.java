package com.icebear2n2.goodplace.domain.repository;

import com.icebear2n2.goodplace.domain.entity.AuthCode;
import com.icebear2n2.goodplace.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {
    AuthCode findByUserAndCode(User user, String code);
    AuthCode findByPhoneAndCode(String phone, String code);
}