package com.example.SocialMediaProject.userService.controller;

import com.example.SocialMediaProject.userService.dto.LoginDto;
import com.example.SocialMediaProject.userService.dto.LoginResponseDto;
import com.example.SocialMediaProject.userService.dto.SignUpDto;
import com.example.SocialMediaProject.userService.dto.Userdto;
import com.example.SocialMediaProject.userService.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Userdto> signup(@RequestBody SignUpDto signUpDto){
        log.info("creating user");
        Userdto userdto=userService.signUp(signUpDto);
        return ResponseEntity.ok(userdto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){

        LoginResponseDto loginResponseDto=userService.login(loginDto);

        return ResponseEntity.ok(loginResponseDto);

    }



}
