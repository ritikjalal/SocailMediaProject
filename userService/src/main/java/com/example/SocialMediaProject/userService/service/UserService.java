package com.example.SocialMediaProject.userService.service;

import com.example.SocialMediaProject.userService.dto.LoginDto;
import com.example.SocialMediaProject.userService.dto.LoginResponseDto;
import com.example.SocialMediaProject.userService.dto.SignUpDto;
import com.example.SocialMediaProject.userService.dto.Userdto;
import org.springframework.stereotype.Service;

@Service
public interface UserService{
    Userdto signUp(SignUpDto signUpDto);
    LoginResponseDto login(LoginDto loginDto);
}
