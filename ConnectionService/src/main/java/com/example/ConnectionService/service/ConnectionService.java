package com.example.ConnectionService.service;

import com.example.ConnectionService.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConnectionService {

    List<Person> getDegreeConnectionOfUser(Long userId);
}
