package com.example.SocialMediaProject.userService.repo;

import com.example.SocialMediaProject.userService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {


    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
