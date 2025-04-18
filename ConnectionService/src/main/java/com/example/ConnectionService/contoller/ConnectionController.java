package com.example.ConnectionService.contoller;


import com.example.ConnectionService.entity.Person;
import com.example.ConnectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
