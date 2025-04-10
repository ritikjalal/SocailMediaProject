package com.example.SocialMediaProject.userService.controller;

import com.example.SocialMediaProject.userService.dto.LoginDto;
import com.example.SocialMediaProject.userService.dto.SignUpDto;
import com.example.SocialMediaProject.userService.dto.Userdto;
import com.example.SocialMediaProject.userService.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Userdto> signup(@RequestBody SignUpDto signUpDto){
        Userdto userdto=userService.signUp(signUpDto);
        return ResponseEntity.ok(userdto);
    }

    @GetMapping("/login")
    public ResponseEntity<Userdto> login(@RequestBody LoginDto loginDto){

        Userdto userdto=userService.login(loginDto);

        return ResponseEntity.ok(userdto);

    }



}
