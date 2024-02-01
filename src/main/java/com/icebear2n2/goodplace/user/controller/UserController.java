package com.icebear2n2.goodplace.user.controller;

import com.icebear2n2.goodplace.domain.request.LoginRequest;
import com.icebear2n2.goodplace.domain.request.SignupRequest;
import com.icebear2n2.goodplace.domain.request.UpdateUserDataRequest;
import com.icebear2n2.goodplace.domain.response.UserResponse;
import com.icebear2n2.goodplace.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Map<String, String> token = userService.authenticateUser(loginRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<UserResponse.UserData>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest request = PageRequest.of(page, size);
        Page<UserResponse.UserData> usersPage = userService.getAll(request);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDataRequest updateUserDataRequest) {
        return new ResponseEntity<>(userService.updateUser(userId, updateUserDataRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeUser(@RequestParam Long userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}