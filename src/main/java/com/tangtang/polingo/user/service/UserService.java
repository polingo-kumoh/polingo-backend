package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.global.constant.UserRole;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.security.service.JwtService;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String login(LoginType loginType, UserInfo userInfo) {
        User user = userRepository.findByLoginTypeAndProviderId(loginType, userInfo.getId())
                .orElseGet(() -> createUser(loginType, userInfo));

        return jwtService.createToken(user);
    }

    private User createUser(LoginType loginType, UserInfo userInfo) {
        User newUser = User.builder()
                .nickname(userInfo.getName())
                .language(Language.ENGLISH)
                .role(UserRole.COMMON)
                .loginType(loginType)
                .providerId(userInfo.getId())
                .build();
        return userRepository.save(newUser);
    }
}
