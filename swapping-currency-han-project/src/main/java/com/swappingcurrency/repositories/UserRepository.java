package com.swappingcurrency.repositories;

import com.swappingcurrency.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String username);
}
