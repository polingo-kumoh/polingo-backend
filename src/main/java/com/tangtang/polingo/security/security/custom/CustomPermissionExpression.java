package com.tangtang.polingo.security.security.custom;

import com.tangtang.polingo.news.repository.NewsScrapRepository;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPermissionExpression implements PermissionEvaluator {
    private final WordSetRepository wordSetRepository;
    private final NewsScrapRepository newsScrapRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;  // 일반적으로 이 메소드는 사용하지 않을 수도 있습니다.
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || targetType == null || permission == null) {
            return false;
        }

        Long userId = ((UserWrapper) authentication.getPrincipal()).getUser().getId();
        Long entityId = ((Long) targetId);

        return switch (targetType) {
            case "WordSet" -> checkWordSetPermission(userId, entityId, permission.toString());
            case "NewsScrap" -> checkNewsScrapPermission(userId, entityId, permission.toString());
            default -> false;
        };
    }

    private boolean checkWordSetPermission(Long userId, Long wordSetId, String permission) {
        return wordSetRepository.findById(wordSetId)
                .map(wordSet -> wordSet.getUser().getId().equals(userId) && checkPermission(wordSet, permission))
                .orElse(false);
    }

    private boolean checkNewsScrapPermission(Long userId, Long newsScrapId, String permission) {
        return newsScrapRepository.findById(newsScrapId)
                .map(newsScrap -> newsScrap.getUser().getId().equals(userId) && checkPermission(newsScrap, permission))
                .orElse(false);
    }

    private boolean checkPermission(Object domainObject, String permission) {
        // 여기에 추가적인 권한 검사 로직을 구현할 수 있습니다.
        return true;
    }
}
