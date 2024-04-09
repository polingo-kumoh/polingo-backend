package com.tangtang.polingo.security.service;

import com.tangtang.polingo.security.exception.JwtAuthenticationException;
import com.tangtang.polingo.security.wrapper.UserWrapper;
import com.tangtang.polingo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static void validateUserInfo(String[] idAndRoleArray) {
        if (idAndRoleArray.length != 2 || idAndRoleArray[0].isEmpty() || idAndRoleArray[1].isEmpty()) {
            throw new JwtAuthenticationException("페이로드가 유효하지 않습니다!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userInfo) throws UsernameNotFoundException {
        String[] idAndRoleArray = userInfo.split(":");
        validateUserInfo(idAndRoleArray);

        String id = idAndRoleArray[0];

        return loadMemberById(id);
    }

    private UserDetails loadMemberById(String id) {
        return userRepository.findById(Long.valueOf(id))
                .map(UserWrapper::new)
                .orElseThrow(() -> new JwtAuthenticationException("해당 토큰과 맞는 사용자가 존재하지 않습니다!"));
    }
}
