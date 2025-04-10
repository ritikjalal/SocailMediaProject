package com.example.SocialMediaProject.postService.repo;

import com.example.SocialMediaProject.postService.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepo extends JpaRepository<PostLikeEntity,Long> {
    boolean existsByUserIdAndPostId(Long userId,Long postId);

    void deleteByUserIdAndPostId(Long userId,Long postId);
}
