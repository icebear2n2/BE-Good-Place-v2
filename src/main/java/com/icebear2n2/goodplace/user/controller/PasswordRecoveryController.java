package com.icebear2n2.goodplace.user.controller;

import com.icebear2n2.goodplace.domain.request.PasswordRecoveryRequest;
import com.icebear2n2.goodplace.domain.request.UserIdRequest;
import com.icebear2n2.goodplace.user.service.PasswordRecoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/password/recovery")
public class PasswordRecoveryController {

    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping
    public ResponseEntity<String> requestCode(@RequestBody UserIdRequest userIdRequest
    ) {
        passwordRecoveryService.requestPasswordRecovery(userIdRequest);
        return new ResponseEntity<>("SEND AUTH CODE SUCCESSFULLY.", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<String> resetPassword(
            @RequestBody
            PasswordRecoveryRequest passwordRecoveryRequest) {
        passwordRecoveryService.verifyAuthCodeAndResetPassword(passwordRecoveryRequest);
        return new ResponseEntity<>("Password reset was successful.", HttpStatus.OK);
    }
}