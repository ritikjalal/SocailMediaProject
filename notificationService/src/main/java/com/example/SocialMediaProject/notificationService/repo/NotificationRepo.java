package com.example.SocialMediaProject.notificationService.repo;


import com.example.SocialMediaProject.notificationService.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationEntity,Long> {

}
