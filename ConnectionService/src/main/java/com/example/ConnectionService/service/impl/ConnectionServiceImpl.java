package com.example.ConnectionService.service.impl;

import com.example.ConnectionService.entity.Person;
import com.example.ConnectionService.repo.PersonRepo;
import com.example.ConnectionService.service.ConnectionService;
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
}
