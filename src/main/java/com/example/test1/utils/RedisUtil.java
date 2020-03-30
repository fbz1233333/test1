package com.example.test1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    static Logger logger = LoggerFactory.getLogger(RedisUtil.class);


    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public String get(final  String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key,String value){
        boolean result=false;
        try{
            redisTemplate.opsForValue().set(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean getAndSet(final String key,String value){
        boolean result=false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
    public boolean count(final String key,String value){
        logger.info("比较中....");

        boolean result=false;
        try {
            result= value.equals(redisTemplate.opsForValue().get(key));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key){
        boolean result=false;
        try{
            redisTemplate.delete(key);
            result=true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
}
