package com.example.SocialMediaProject.userService.exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String mssg) {
        super(mssg);
    }
}
