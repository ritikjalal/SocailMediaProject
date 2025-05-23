package com.example.SocialMediaProject.postService.service.impl;

import com.example.SocialMediaProject.postService.auth.AuthContextHolder;
import com.example.SocialMediaProject.postService.client.ConnectionServiceClient;
import com.example.SocialMediaProject.postService.dto.PersonDto;
import com.example.SocialMediaProject.postService.dto.PostCreateDto;
import com.example.SocialMediaProject.postService.dto.PostDto;
import com.example.SocialMediaProject.postService.entity.PostEntity;
import com.example.SocialMediaProject.postService.event.PostCreatedEvent;
import com.example.SocialMediaProject.postService.exception.ResourceException;
import com.example.SocialMediaProject.postService.repo.PostRepo;
import com.example.SocialMediaProject.postService.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final ConnectionServiceClient connectionServiceClient;

    private final KafkaTemplate<Long, PostCreatedEvent> postCreatedEventKafkaTemplate;

    @Override
    public PostDto createPost(PostCreateDto postCreateDto) {

        PostEntity postEntity=modelMapper.map(postCreateDto,PostEntity.class);
        postEntity.setUserId(AuthContextHolder.getCurrentUserId());
        postEntity=postRepo.save(postEntity);


        //kafka send notification to each connection
        //====
        List<PersonDto> personDtos=connectionServiceClient.getListOfPerson(AuthContextHolder.getCurrentUserId());


        for (PersonDto personDto:personDtos){
            PostCreatedEvent postCreatedEvent=PostCreatedEvent.builder()
                    .postId(postEntity.getId())
                    .content(postEntity.getContent())
                    .userId(personDto.getUserId())
                    .ownerUserId(AuthContextHolder.getCurrentUserId())
                    .build();
            postCreatedEventKafkaTemplate.send("post-created-topic",postCreatedEvent);
        }

        //====

        return modelMapper.map(postEntity,PostDto.class);

    }

    @Override
    public PostDto getPostById(Long postid) {
        log.info("getting user post by id:{}",postid);

        PostEntity postEntity=postRepo.findById(postid)
                .orElseThrow(()->new ResourceException("Post not found" + postid));
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost(Long userId) {

        log.info("getting all post of user:{}",userId);

        return postRepo.findByUserId(userId).stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());

    }
}
