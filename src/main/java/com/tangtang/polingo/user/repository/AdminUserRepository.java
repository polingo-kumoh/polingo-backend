package com.tangtang.polingo.user.repository;

import com.tangtang.polingo.user.entity.Admin;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findAdminByUsername(String username);

}
