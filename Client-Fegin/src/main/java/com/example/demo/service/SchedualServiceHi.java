package com.example.demo.service;

import com.example.demo.fallback.SchedualSericeHiFallBackFactory;
import com.example.demo.model.User;
import com.harry.model.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "service-hi",fallbackFactory = SchedualSericeHiFallBackFactory.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/user/second",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    User getUser(@PathVariable(value = "userId") Integer userId);
}
