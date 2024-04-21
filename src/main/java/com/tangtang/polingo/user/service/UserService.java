package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.dto.UserResponse;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<UserResponse> getUser(User user) {
        if (entityManager.contains(user)) {
            log.info("User entity is in a persistent state.");
        } else {
            log.info("User entity is not in a persistent state.");
        }

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .defaultLanguage(user.getLanguage())
                .build();

        return ResponseEntity.ok(userResponse);
    }


    public void updateNickname(User user, String updateName) {
        validateNickname(updateName);
        user.updateNIckName(updateName);
        userRepository.save(user);
    }

    public void updateLanguage(User user, String languageCode) {
        Language newLanguage = Language.fromCode(languageCode);
        user.updateLanguage(newLanguage);
        userRepository.save(user);
    }

    private void validateNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 닉네임입니다.");
        }
    }
}
