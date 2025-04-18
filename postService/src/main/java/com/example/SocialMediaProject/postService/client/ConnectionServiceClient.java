package com.example.SocialMediaProject.postService.client;

import com.example.SocialMediaProject.postService.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ConnectionService",path = "/connections")
public interface ConnectionServiceClient {

    @GetMapping("/{userId}")
    public List<PersonDto> getListOfPerson(@PathVariable("userId") Long userId);
}
