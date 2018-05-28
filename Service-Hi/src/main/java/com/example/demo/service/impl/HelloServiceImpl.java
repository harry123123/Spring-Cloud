package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.HelloService;
import com.harry.model.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class HelloServiceImpl implements HelloService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;

    public String sayHello(String name){
        return "hello, " + name + " !";
    }

    @Override
    public User getUser(Integer userId) {
        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://SERVICE-USER/user/{1}", User.class, 1);
        return forEntity.getBody();
    }
}
