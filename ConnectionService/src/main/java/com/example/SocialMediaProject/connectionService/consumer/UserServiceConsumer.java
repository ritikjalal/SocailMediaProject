package com.example.SocialMediaProject.connectionService.consumer;



import com.example.SocialMediaProject.connectionService.service.impl.PersonService;
import com.example.SocialMediaProject.userService.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceConsumer {

    private final PersonService personService;

    @KafkaListener(topics = "user-created-topic")
    public void handlePersonCreated(UserCreatedEvent userCreatedEvent){

        log.info("New person created :{}",userCreatedEvent);

        personService.createPerson(userCreatedEvent.getUserId(),userCreatedEvent.getName());

    }



}
