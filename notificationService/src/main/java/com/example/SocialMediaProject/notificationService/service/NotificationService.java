package com.example.SocialMediaProject.notificationService.service;

import com.example.SocialMediaProject.notificationService.entity.NotificationEntity;
import com.example.SocialMediaProject.notificationService.repo.NotificationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepo notificationRepo;


    public void addNotifications(NotificationEntity notificationEntity){

        log.info("Saving notification to db,message :{}",notificationEntity.getMessage());

        notificationEntity=notificationRepo.save(notificationEntity);

        //TODO: send mailer to email

    }


}
