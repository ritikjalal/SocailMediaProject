package com.example.SocialMediaProject.postService.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic postCreated(){
        return new NewTopic("post-created-topic",3,(short) 1);
    }

    @Bean
    public NewTopic postLike(){
        return new NewTopic("post-like-topic",3,(short) 1);
    }

}
