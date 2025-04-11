package com.example.ConnectionService.entity;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Person {


    @Id
    @GeneratedValue
    private Long id;


    private Long userId;
    private String name;


}
