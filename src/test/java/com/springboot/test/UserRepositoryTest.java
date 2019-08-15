package com.springboot.test;

import demo.springboot.Application;
import demo.springboot.mapper.UserRepository;
import demo.springboot.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@program: UserRepositoryTest
 *@description: SpringBoot中使用SpringDataJPA的测试类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void test(){
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = dateFormat.format(date);
//        userRepository.save(new User("aa","aa123456","aa@126.com","aa",formattedDate));
//        userRepository.save(new User("bb","bb123456","bb@126.com","bb",formattedDate));
//        userRepository.save(new User("cc","cc123456","cc@126.com","cc",formattedDate));
//        Assert.assertEquals(3,userRepository.findAll().size());
//        Assert.assertEquals("bb",userRepository.findByUserNameOrEmail("bb","bb@126.com").getNickName());
//        userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void testFindAll(){
        int page = 0;
        int size = 1;
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<User> all = userRepository.findAll(pageable);
        Assert.assertEquals(1,all.getContent().size());
        Assert.assertEquals(2,all.getTotalPages());
    }

    @Test
    public void testFindByNickName(){
        int page = 0;
        int size = 1;
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<User> all = userRepository.findByNickName("bb",pageable);
        Assert.assertEquals(1,all.getContent().size());
        Assert.assertEquals(1,all.getTotalPages());
    }
}
