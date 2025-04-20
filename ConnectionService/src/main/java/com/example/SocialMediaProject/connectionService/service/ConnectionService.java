package com.example.SocialMediaProject.connectionService.service;

import com.example.SocialMediaProject.connectionService.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConnectionService {

    List<Person> getDegreeConnectionOfUser(Long userId);

    void sendRequest(Long toUserId);
}
