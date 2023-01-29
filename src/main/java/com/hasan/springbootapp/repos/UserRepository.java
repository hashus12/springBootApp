package com.hasan.springbootapp.repos;

import com.hasan.springbootapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
