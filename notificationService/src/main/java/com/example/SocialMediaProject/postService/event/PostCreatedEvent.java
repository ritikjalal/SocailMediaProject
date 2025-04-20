package com.example.SocialMediaProject.postService.event;


import lombok.Builder;
import lombok.Data;

@Data
public class PostCreatedEvent {

    private Long postId;
    private Long userId;
    private Long ownerUserId;
    private String content;

}
