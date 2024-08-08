package com.Backend.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Backend.java.demo.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);
}
