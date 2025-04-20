package com.example.SocialMediaProject.postService.service.impl;

import com.example.SocialMediaProject.postService.auth.AuthContextHolder;
import com.example.SocialMediaProject.postService.entity.PostEntity;
import com.example.SocialMediaProject.postService.entity.PostLikeEntity;
import com.example.SocialMediaProject.postService.event.PostLikeEvent;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepo postLikeRepo;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, PostLikeEvent> postLikeEventKafkaTemplate;

    @Override
    @Transactional
    public void postLike(Long postId) {

        Long userId= AuthContextHolder.getCurrentUserId();

        log.info("like in the post:{} by : {} ",postId,userId);

        PostEntity postEntity=postRepo.findById(postId)
                .orElseThrow(()->new ResourceException("Post not found" + postId));

        boolean like=postLikeRepo.existsByUserIdAndPostId(userId,postId);

        if(like) throw new BadRequest("Already liked post");

        PostLikeEntity postLikeEntity=new PostLikeEntity();
        postLikeEntity.setPostId(postId);
        postLikeEntity.setUserId(userId);

        postLikeRepo.save(postLikeEntity);


        //sent notification via kafka

        PostLikeEvent postLikeEvent=PostLikeEvent.builder()
                .postId(postEntity.getId())
                .ownerUserId(postEntity.getUserId())
                .likeByUserId(userId)
                .build();

        postLikeEventKafkaTemplate.send("post-like-topic",postLikeEvent);

        //




    }

    @Override
    @Transactional
    public void postDelete(Long postId) {

        Long userId= AuthContextHolder.getCurrentUserId();

        log.info("unlike in the post:{} by : {} ",postId,userId);

        postRepo.findById(postId)
                .orElseThrow(()->new ResourceException("Post not found" + postId));

        boolean like=postLikeRepo.existsByUserIdAndPostId(userId,postId);

        if(!like) throw new BadRequest("cannot unlike post");

        postLikeRepo.deleteByUserIdAndPostId(userId,postId);

    }
}
