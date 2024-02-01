package com.icebear2n2.goodplace.user.service;

import com.icebear2n2.goodplace.domain.entity.AuthCode;
import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.AuthCodeRequest;
import com.icebear2n2.goodplace.domain.request.CheckAuthCodeRequest;
import com.icebear2n2.goodplace.domain.request.PasswordRecoveryRequest;
import com.icebear2n2.goodplace.domain.request.UserIdRequest;
import com.icebear2n2.goodplace.exception.ErrorCode;
import com.icebear2n2.goodplace.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
    private final UserRepository userRepository;
    private final AuthCodeService authCodeService;


    public void requestPasswordRecovery(UserIdRequest userIdRequest) {
        User user = userRepository.findById(userIdRequest.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
        // 인증 코드 SMS 전송
        authCodeService.sendAuthCode(new AuthCodeRequest(userIdRequest.getUserId(), user.getPhone()));
    }

    public void verifyAuthCodeAndResetPassword(PasswordRecoveryRequest passwordRecoveryRequest) {
        User user = userRepository.findById(passwordRecoveryRequest.getUserId())
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        // Step 1: 인증코드가 일치하는지 확인하고 해당 인증코드 객체 반환 받기
        CheckAuthCodeRequest checkAuthCodeRequest = new CheckAuthCodeRequest(
                user.getUserId(), user.getPhone(), passwordRecoveryRequest.getCode());
        AuthCode validAuthCode = authCodeService.getValidAuthCode(checkAuthCodeRequest.getPhone(), checkAuthCodeRequest.getCode());

        // Step 2: 비밀번호가 일치하는지 확인
        confirmNewPassword(passwordRecoveryRequest.getNewPassword(), passwordRecoveryRequest.getConfirmNewPassword());

        // Step 3: 비밀번호 확인 메서드가 통과하면 인증코드 완료 필드 업데이트
        authCodeService.completedSaveAuthCode(validAuthCode);

        // Step 4: 비밀번호 재설정
        resetPassword(user, passwordRecoveryRequest.getNewPassword());
    }


    private void resetPassword(User user, String newPassword) {
        String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    private void confirmNewPassword(String newPassword, String confirmNewPassword) {
        if (!confirmNewPassword.equals(newPassword)) {
            throw new ServiceException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
