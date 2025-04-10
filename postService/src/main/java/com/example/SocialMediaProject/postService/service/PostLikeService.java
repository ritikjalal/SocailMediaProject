package com.example.SocialMediaProject.postService.service;

import org.springframework.stereotype.Service;

@Service
public interface PostLikeService {
    void postLike(Long postId);

    void postDelete(Long postId);
}
