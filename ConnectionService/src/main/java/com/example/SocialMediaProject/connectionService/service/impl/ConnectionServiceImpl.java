package com.example.SocialMediaProject.connectionService.service.impl;

import com.example.SocialMediaProject.connectionService.auth.AuthContextHolder;
import com.example.SocialMediaProject.connectionService.entity.Person;
import com.example.SocialMediaProject.connectionService.repo.PersonRepo;
import com.example.SocialMediaProject.connectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

    private final PersonRepo personRepo;


    public List<Person> getDegreeConnectionOfUser(Long userId){

        log.info("getting first degree connection of userId:{}",userId);
        return personRepo.getFirstDegreeConnection(userId);

    }

    public void sendRequest(Long toUserId){
        Long fromUserId=AuthContextHolder.getCurrentUserId();

    }


}
