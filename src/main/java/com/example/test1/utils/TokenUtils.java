package com.example.test1.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.test1.mapper.FindIdAndUsernameByUsernameAndPasswordResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TokenUtils {
    public String getToken(FindIdAndUsernameByUsernameAndPasswordResult user) {
        String token="";
        token= JWT.create().withAudience(user.getId()).withAudience(user.getUsername()).withAudience(new Random().toString())
                .sign(Algorithm.HMAC256(new Date().toString()));

        return token;
    }
}
