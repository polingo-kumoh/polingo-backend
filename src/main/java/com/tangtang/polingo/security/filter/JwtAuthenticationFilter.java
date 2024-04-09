package com.tangtang.polingo.security.filter;

import com.tangtang.polingo.security.exception.JwtAuthenticationException;
import com.tangtang.polingo.security.service.CustomUserDetailsService;
import com.tangtang.polingo.security.service.JwtService;
import com.tangtang.polingo.security.util.JwtErrorResponseWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            authenticateRequest(request);
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            JwtErrorResponseWriter.write(response, e.getMessage());
        }
    }

    private void authenticateRequest(HttpServletRequest request) {
        Optional<String> tokenOptional = jwtService.extractTokenFromHeader(request);

        tokenOptional.ifPresent(token -> {
            Authentication authentication = authenticateWithToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }


    private Authentication authenticateWithToken(String token) {
        String userInfo = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)
                .map(jwtService::validateTokenAndGetSubject)
                .orElseThrow(() -> new JwtAuthenticationException("페이로드가 올바르지 않습니다"));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userInfo);
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }
}
