package com.example.SocialMediaProject.userService.utils;

import static org.mindrot.jbcrypt.BCrypt.*;

public class BCrypt {

    public static String hash(String s){
        return hashpw(s, gensalt(12));
    }

    public static boolean matchPassword(String password,String hashpassword){
       return checkpw(password,hashpassword);
    }

}
