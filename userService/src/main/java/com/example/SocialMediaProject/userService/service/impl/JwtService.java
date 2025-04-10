package com.example.SocialMediaProject.userService.service.impl;

import com.example.SocialMediaProject.userService.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secretKey}")
    private String jwtKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
    }


    //adding access token
    public String generateAccessToken(UserEntity userEntity){
        return Jwts.builder()
                .subject(userEntity.getId().toString())
                .claim("email",userEntity.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }


    //adding refresh token
    public String generateRefreshToken(UserEntity entity){
        return Jwts.builder()
                .subject(entity.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    //how to get something from the jwt token like suppose i want user id
    public Long getReturnuserId(String token){
        token = token.replaceAll("\\s", "");
        System.out.println("new Refrsh token    "+token);
        Claims claims=Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long userid=Long.valueOf(claims.getSubject());
        System.out.println("user id  "+ userid);
        return userid;
    }

}
