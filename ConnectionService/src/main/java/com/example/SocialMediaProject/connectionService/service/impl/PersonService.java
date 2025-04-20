package com.example.SocialMediaProject.connectionService.service.impl;



import com.example.SocialMediaProject.connectionService.entity.Person;
import com.example.SocialMediaProject.connectionService.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonRepo personRepo;


    public void createPerson(Long userId,String name){
        Person person=Person.builder().userId(userId).name(name).build();
        personRepo.save(person);
    }


}
