package com.icebear2n2.goodplace.user.service;

import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.LoginRequest;
import com.icebear2n2.goodplace.domain.request.SignupRequest;
import com.icebear2n2.goodplace.domain.request.UpdateUserDataRequest;
import com.icebear2n2.goodplace.domain.response.UserResponse;
import com.icebear2n2.goodplace.exception.ErrorCode;
import com.icebear2n2.goodplace.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z]).{8,}$");
    private final Random random;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ErrorCode.USER_NOT_FOUND.toString());
        }
        return user;
    }

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ServiceException(ErrorCode.DUPLICATED_USER_EMAIL);
        }

        validatePassword(request.getPassword());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = request.toEntity(request.getUsername(), request.getEmail(), request.getAge(), request.getGender(), encodedPassword);

        try {
            user.setNickname(generateUniqueNickname());
            userRepository.save(user);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    public void validatePassword(String password) {
        if(!password.matches(String.valueOf(PASSWORD_PATTERN))) {
            throw new ServiceException(ErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    public Map<String, String> authenticateUser(LoginRequest loginRequest) {
        User user = Optional.ofNullable(userRepository.findByEmail(loginRequest.getEmail()))
                .orElseThrow(() -> new ServiceException(ErrorCode.UNAUTHORIZED));

        if (!new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ServiceException(ErrorCode.FAILED_LOGIN);
        }

        return tokenService.generateAndSaveTokens(user);
    }
    @Transactional(readOnly = true)
    public Page<UserResponse.UserData> getAll(PageRequest pageRequest) {
        Page<User> all = userRepository.findAll(pageRequest);
        return all.map(UserResponse.UserData::new);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserDataRequest request) {
        if (!userRepository.existsById(userId)) {
            return UserResponse.failure(ErrorCode.USER_NOT_FOUND.toString());
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }

        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }

        try {
            userRepository.save(user);
            return UserResponse.success(user);
        } catch (Exception e) {
            handleException("ERROR OCCURRED WHILE UPDATING", e);
            return UserResponse.failure("Failed to update user data.");
        }

    }

    @Transactional
    public void removeUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

        user.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        user.setRole(null);
        userRepository.save(user);
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void purgeDeletedUsers() {
        Timestamp threshold = new Timestamp(System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000));
        userRepository.deleteByDeletedAtBefore(threshold);
    }

    public String generateUniqueNickname() {
        while (true) {
            String nickname = String.valueOf(random.nextInt(900000) + 100000);
            if (userRepository.findByNickname(nickname) == null) {
                return nickname;
            }
        }
    }

    private void handleException(String message, Exception e) {
        LOGGER.error(message, e);
        throw new ServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
