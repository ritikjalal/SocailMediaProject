package com.example.SocialMediaProject.postService.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
}
