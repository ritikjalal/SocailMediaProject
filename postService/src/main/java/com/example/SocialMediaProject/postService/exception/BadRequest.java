package com.example.SocialMediaProject.postService.exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String mssg) {
        super(mssg);
    }
}
