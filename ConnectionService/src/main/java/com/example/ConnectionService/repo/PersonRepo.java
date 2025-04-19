package com.example.ConnectionService.repo;

import com.example.ConnectionService.entity.Person;
import feign.Param;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.*;

public interface PersonRepo extends Neo4jRepository<Person,Long> {

    Optional<Person> findByUserId(Long userId);

    @Query("MATCH (personA:Person)-[:CONNECTED_TO]-(personB:Person) WHERE personA.userId = $userId RETURN personB")
    List<Person> getFirstDegreeConnection(Long userId);

}
