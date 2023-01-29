package com.hasan.springbootapp.repos;

import com.hasan.springbootapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
