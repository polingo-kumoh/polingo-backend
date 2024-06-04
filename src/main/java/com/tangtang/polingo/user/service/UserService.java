package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.user.constant.LoginType;
import com.tangtang.polingo.user.constant.UserRole;
import com.tangtang.polingo.user.dto.UserResponse;
import com.tangtang.polingo.user.entity.Admin;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.repository.AdminUserRepository;
import com.tangtang.polingo.user.repository.UserRepository;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final WordSetRepository wordSetRepository;
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;
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

    @Transactional
    public void updateNickname(User user, String updateName) {
        validateNickname(updateName);
        user.updateNIckName(updateName);
        userRepository.save(user);
    }

    @Transactional
    public void updateLanguage(User user, String languageCode) {
        Language newLanguage = Language.fromCode(languageCode);
        user.updateLanguage(newLanguage);
        userRepository.save(user);
    }

    public void createAdmin(String username, String password, String nickname){
        Admin admin = Admin.builder()
                .username(username)
                .password(password)
                .name(nickname)
                .build();

        adminUserRepository.save(admin);
    }

    @Transactional
    public User createUser(LoginType loginType, UserInfo userInfo) {
        User newUser = User.builder()
                .nickname(userInfo.getName())
                .language(Language.ENGLISH)
                .role(UserRole.COMMON)
                .loginType(loginType)
                .providerId(userInfo.getId())
                .build();
        newUser = userRepository.save(newUser);
        createDefaultWordSets(newUser);
        return newUser;
    }

    private void createDefaultWordSets(User user) {
        createDefaultWordSet(user, "영어 기본 단어장", Language.ENGLISH);
        createDefaultWordSet(user, "일본어 기본 단어장", Language.JAPANESE);
    }

    private void createDefaultWordSet(User user, String name, Language language) {
        WordSet wordSet = WordSet.builder()
                .name(name)
                .language(language)
                .isDefault(true)
                .user(user)
                .build();
        wordSetRepository.save(wordSet);
    }

    private void validateNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 닉네임입니다.");
        }
    }
}
