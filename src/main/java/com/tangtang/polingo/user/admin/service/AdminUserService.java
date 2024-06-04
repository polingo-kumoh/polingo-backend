package com.tangtang.polingo.user.admin.service;

import com.tangtang.polingo.security.jwt.JwtProvider;
import com.tangtang.polingo.user.admin.dto.AdminUserLoginRequest;
import com.tangtang.polingo.user.admin.dto.AdminUserSignUpRequest;
import com.tangtang.polingo.user.entity.Admin;
import com.tangtang.polingo.user.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void createAdmin(AdminUserSignUpRequest request) {
        Admin admin = Admin.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .name(request.nickname())
                .build();
        adminUserRepository.save(admin);
    }

    public String loginAdmin(AdminUserLoginRequest request) {
        Admin admin = adminUserRepository.findAdminByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));

        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtProvider.createToken(admin.getId(), "ADMIN");
    }
}
