package com.zfnotes.mapper;

import com.zfnotes.entities.User;
import com.zfnotes.entities.BillProvider;
import com.zfnotes.entities.User;

import java.util.List;

/**
 * 使用Mybatis的注解版
 */
// @Mapper // 指定这是操作数据库的mapper
public interface UserMapper {
    User getUserByUserName(String username);

    List<User> getUser(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int deleteUser(Integer id);

    int updateUser(User user);

}
