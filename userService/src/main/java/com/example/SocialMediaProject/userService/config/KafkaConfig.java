package com.example.SocialMediaProject.userService.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic postCreated(){

        return new NewTopic("user-created-topic",3,(short) 1);
    }


}
