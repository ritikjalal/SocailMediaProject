package com.example.SocialMediaProject.postService.controller;

import com.example.SocialMediaProject.postService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostLikedController {

    private final PostLikeService postLikeService;

    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> postLike(@PathVariable("postId") Long postId ){

        postLikeService.postLike(postId);
        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> postDelete(@PathVariable("postId") Long postId ){

        postLikeService.postDelete(postId);
        return ResponseEntity.noContent().build();

    }


}
