package com.example.SocialMediaProject.notificationService.consumer;


import com.example.SocialMediaProject.notificationService.entity.NotificationEntity;
import com.example.SocialMediaProject.notificationService.service.NotificationService;
import com.example.SocialMediaProject.postService.event.PostCreatedEvent;
import com.example.SocialMediaProject.postService.event.PostLikeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostConsumer {

    private final NotificationService notificationService;


    @KafkaListener(topics = "post-created-topic")
    public void handlePostCretaedEvent(PostCreatedEvent postCreatedEvent){

        log.info("reciving notification:{}",postCreatedEvent);

        String message=String.format("your friend :%d has created the post has been created by :%s",
                postCreatedEvent.getOwnerUserId(),postCreatedEvent.getContent());

        NotificationEntity notificationEntity=NotificationEntity.builder()
                .message(message)
                .userId(postCreatedEvent.getUserId())
                .build();

        notificationService.addNotifications(notificationEntity);

    }


    @KafkaListener(topics = "post-like-topic")
    public void handlePostLikeEvent(PostLikeEvent postLikeEvent){

        log.info("reciving notification:{}",postLikeEvent);

        String message=String.format("your friend :%d has liked the post :%d",
                postLikeEvent.getLikeByUserId(),postLikeEvent.getPostId());

        NotificationEntity notificationEntity=NotificationEntity.builder()
                .message(message)
                .userId(postLikeEvent.getOwnerUserId())
                .build();

        notificationService.addNotifications(notificationEntity);

    }



}
