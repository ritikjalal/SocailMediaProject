package com.example.SocialMediaProject.userService.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {

    private String email;
    private String password;
    private String name;

}
