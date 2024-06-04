package com.tangtang.polingo.user.repository;

import com.tangtang.polingo.user.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminUserRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findAdminByUsername(String username);

}
