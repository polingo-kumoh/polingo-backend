package com.tangtang.polingo.user.repository;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLoginTypeAndProviderId(LoginType loginType, String providerId);
}
