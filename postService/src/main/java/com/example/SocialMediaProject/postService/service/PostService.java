package com.example.SocialMediaProject.postService.service;

import com.example.SocialMediaProject.postService.dto.PostCreateDto;
import com.example.SocialMediaProject.postService.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {


    PostDto createPost(PostCreateDto postCreateDto);

    PostDto getPostById(Long postid);

    List<PostDto> getAllPost(Long userId);
}
