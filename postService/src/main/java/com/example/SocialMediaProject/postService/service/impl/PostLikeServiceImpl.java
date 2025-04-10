package com.example.SocialMediaProject.postService.service.impl;

import com.example.SocialMediaProject.postService.entity.PostEntity;
import com.example.SocialMediaProject.postService.entity.PostLikeEntity;
import com.example.SocialMediaProject.postService.exception.BadRequest;
import com.example.SocialMediaProject.postService.exception.ResourceException;
import com.example.SocialMediaProject.postService.repo.PostLikeRepo;
import com.example.SocialMediaProject.postService.repo.PostRepo;
import com.example.SocialMediaProject.postService.service.PostLikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepo postLikeRepo;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void postLike(Long postId) {
        Long userId=123L;
        log.info("like in the post:{} by : {} ",postId,userId);

        postRepo.findById(postId)
                .orElseThrow(()->new ResourceException("Post not found" + postId));

        boolean like=postLikeRepo.existsByUserIdAndPostId(userId,postId);

        if(like) throw new BadRequest("Already liked post");

        PostLikeEntity postLikeEntity=new PostLikeEntity();
        postLikeEntity.setPostId(postId);
        postLikeEntity.setUserId(userId);

        postLikeRepo.save(postLikeEntity);


        //sent notification



    }

    @Override
    @Transactional
    public void postDelete(Long postId) {

        Long userId=123L;
        log.info("unlike in the post:{} by : {} ",postId,userId);

        postRepo.findById(postId)
                .orElseThrow(()->new ResourceException("Post not found" + postId));

        boolean like=postLikeRepo.existsByUserIdAndPostId(userId,postId);

        if(!like) throw new BadRequest("cannot unlike post");

        postLikeRepo.deleteByUserIdAndPostId(userId,postId);

    }
}
