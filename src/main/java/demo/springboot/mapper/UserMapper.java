package demo.springboot.mapper;

import demo.springboot.model.User;

import java.util.List;

/**
 *@program: UserMapper
 *@description: Springboot整合mybaits测试mapper类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public interface UserMapper {
    int save(User user);
    int update(User user);
    int deleteById(int id);
    User selectById(int id);
    List<User> selectAll();
}
