package com.example.SocialMediaProject.postService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonDto {

    private Long id;
    private Long userId;
    private String name;

}
