package com.example.SocialMediaProject.postService.auth;


public class AuthContextHolder {


    private static final ThreadLocal<Long> curUserId=new ThreadLocal<>();

    public static Long getCurrentUserId(){
        return curUserId.get();
    }

    static void setCurUserId(Long userId){
        curUserId.set(userId);
    }

    static void clear(){
        curUserId.remove();
    }


}
