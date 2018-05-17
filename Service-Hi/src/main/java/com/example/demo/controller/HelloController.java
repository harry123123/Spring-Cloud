package com.example.demo.controller;

import com.example.demo.annotations.ExecTime;
import com.example.demo.annotations.RequestDecode;
import com.example.demo.dto.ResponseBean;
import com.example.demo.model.User;
import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Resource
    private HelloService helloService;

    @ExecTime
    @RequestMapping(value = "/first",produces = "application/json",method = RequestMethod.GET)
    public ResponseBean sayHello(@RequestParam String name){


        return new ResponseBean().success(helloService.sayHello(name));
    }

    @ExecTime
    @RequestDecode
    @RequestMapping(value = "/second",produces="application/json",method = RequestMethod.POST)
    public ResponseBean addUser(@RequestBody User user){
        User user1 = new User();

        user1.setName("tom");
        user1.setAge(10);
        user1.setMoney(new BigDecimal("0.01005464321000"));
        user1.setScale(new BigDecimal("0.154600065400"));
        user1.setBirthday(new Date());
        return new ResponseBean().success(user1);
    }
}
