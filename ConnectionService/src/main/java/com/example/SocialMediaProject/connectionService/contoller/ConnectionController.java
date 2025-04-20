package com.example.SocialMediaProject.connectionService.contoller;


import com.example.SocialMediaProject.connectionService.entity.Person;
import com.example.SocialMediaProject.connectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/connections")
public class ConnectionController {

   private final ConnectionService connectionService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Person>> getListOfPerson(@PathVariable("userId") Long userId){

        log.info("userid:{}",userId);

        List<Person> personList=connectionService.getDegreeConnectionOfUser(userId);
        return ResponseEntity.ok(personList);


    }

    @PostMapping("/request")
    public ResponseEntity<Void> connectionRequest(@PathVariable("userId") Long userId){

        connectionService.sendRequest(userId);
        return ResponseEntity.noContent().build();

    }



}
