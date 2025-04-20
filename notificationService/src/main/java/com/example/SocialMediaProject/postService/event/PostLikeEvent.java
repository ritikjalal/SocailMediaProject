package com.example.SocialMediaProject.postService.event;


import lombok.Builder;
import lombok.Data;

@Data
public class PostLikeEvent {

    private Long postId;
    private Long likeByUserId;
    private Long ownerUserId;

}
