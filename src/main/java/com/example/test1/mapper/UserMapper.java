package com.example.test1.mapper;
import org.apache.ibatis.annotations.Param;
import com.example.test1.mapper.FindIdAndUsernameByUsernameAndPasswordResult;

import com.example.test1.entity.User;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAll();

    FindIdAndUsernameByUsernameAndPasswordResult findIdAndUsernameByUsernameAndPassword(@Param("username")String username,@Param("password")String password);


}
