package com.example.SocialMediaProject.postService.repo;

import com.example.SocialMediaProject.postService.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<PostEntity,Long> {
}
