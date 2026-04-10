package com.ems.empmanagamentapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.empmanagamentapp.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}