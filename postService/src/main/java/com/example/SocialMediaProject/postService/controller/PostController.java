package com.example.SocialMediaProject.postService.controller;


import com.example.SocialMediaProject.postService.auth.AuthContextHolder;
import com.example.SocialMediaProject.postService.dto.PostCreateDto;
import com.example.SocialMediaProject.postService.dto.PostDto;
import com.example.SocialMediaProject.postService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {

    private final PostService postService;


    //create post

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateDto postCreateDto){

        log.info("post creating");
        PostDto postDto=postService.createPost(postCreateDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postid") Long postid){


        log.info("USer id is accesing post:{}", AuthContextHolder.getCurrentUserId());
        PostDto postDto= postService.getPostById(postid);
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}/getAllpost")
    public ResponseEntity<List<PostDto>> findUserAllPost(@PathVariable("userId") Long userId){
       List<PostDto> postDtos=postService.getAllPost(userId);
       return ResponseEntity.ok(postDtos);
    }










}
