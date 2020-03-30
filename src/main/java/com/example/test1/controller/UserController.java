package com.example.test1.controller;

import com.example.test1.annoation.UserLoginToken;
import com.example.test1.entity.User;
import com.example.test1.mapper.FindIdAndUsernameByUsernameAndPasswordResult;
import com.example.test1.mapper.UserMapper;
import com.example.test1.result.ExceptionResult;
import com.example.test1.result.UserInfo;
import com.example.test1.utils.RedisUtil;
import com.example.test1.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("login")
    public HashMap<String,Object> login(@RequestBody User loginInfo){
        logger.info("请求的登录信息为:{}",loginInfo);
        HashMap<String,Object> map=new HashMap<>();
        FindIdAndUsernameByUsernameAndPasswordResult result=
                userMapper.findIdAndUsernameByUsernameAndPassword(loginInfo.getUsername(),loginInfo.getPassword());
        if (result==null){
            logger.info("没有这样的用户");
            map.put("RESULT","NO_SUCH_USER");
        }else {
            logger.info("User信息为:{}",result);
            map.put("RESULT","LOGIN_SUCCESS");
            UserInfo userInfo=new UserInfo();
            userInfo.setId(result.getId());
            userInfo.setUsername(result.getUsername());
            userInfo.setToken(tokenUtils.getToken(result));
            redisUtil.set(result.getId(),userInfo.getToken());
            map.put("USER_INFO",userInfo);

        }
        return map;
    }

    @GetMapping("test")
    public List<User> get(){
        return userMapper.findAll();
    }

    @GetMapping("test2")
    @UserLoginToken
    public HashMap get2(){
        logger.info("测试成功");
        HashMap<String,Object> map=new HashMap<>();
        map.put("info","测试成功");
        return map;
    }

    @GetMapping("noToken")
    @CrossOrigin
    public ResponseEntity<ExceptionResult> noToken(){
        ExceptionResult exceptionResult=new ExceptionResult();
        exceptionResult.setInfo("不通过token验证");
        return new ResponseEntity<>(exceptionResult,HttpStatus.BAD_REQUEST);

    }
}
