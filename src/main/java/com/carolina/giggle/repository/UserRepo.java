package com.carolina.giggle.repository;

import com.carolina.giggle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String username);
}
