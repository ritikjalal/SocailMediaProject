package com.example.SocialMediaProject.postService.advice;

import com.example.SocialMediaProject.postService.exception.ResourceException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ApiError> handleException(ResourceException resourceException){

        ApiError apierror=new ApiError(resourceException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apierror,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException badRequestException){

        ApiError apierror=new ApiError(badRequestException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apierror,HttpStatus.BAD_REQUEST);

    }

}
