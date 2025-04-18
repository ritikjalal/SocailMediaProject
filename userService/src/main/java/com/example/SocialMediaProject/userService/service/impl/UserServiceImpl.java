package com.example.SocialMediaProject.userService.service.impl;

import com.example.SocialMediaProject.userService.dto.LoginDto;
import com.example.SocialMediaProject.userService.dto.LoginResponseDto;
import com.example.SocialMediaProject.userService.dto.SignUpDto;
import com.example.SocialMediaProject.userService.dto.Userdto;
import com.example.SocialMediaProject.userService.entity.UserEntity;
import com.example.SocialMediaProject.userService.exception.BadRequest;
import com.example.SocialMediaProject.userService.repo.UserRepo;
import com.example.SocialMediaProject.userService.service.UserService;
import com.example.SocialMediaProject.userService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public Userdto signUp(SignUpDto signUpDto) {

        log.info("Sign up with user:{}",signUpDto.getEmail());

        boolean isUser=userRepo.existsByEmail(signUpDto.getEmail());
        if(isUser) throw new BadRequest("User is already there");

        UserEntity userEntity=modelMapper.map(signUpDto,UserEntity.class);
        userEntity.setPassword(BCrypt.hash(userEntity.getPassword()));
        userEntity=userRepo.save(userEntity);
        return modelMapper.map(userEntity,Userdto.class);

    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {

        log.info("Sign up with user:{}",loginDto.getEmail());

        UserEntity userEntity=userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(()->new BadRequest("Incorrect email and passowrd"));

        boolean isPasswordMatch=BCrypt.matchPassword(loginDto.getPassword(),userEntity.getPassword());

        String token;
        if(isPasswordMatch){
            token=jwtService.generateAccessToken(userEntity);
        }else{
            throw new BadRequest("Incorrect email and passowrd");
        }

        return new LoginResponseDto(token);


    }



}
