package com.ernestogo.first_spring_app.Controller;

import com.ernestogo.first_spring_app.domain.User;
import com.ernestogo.first_spring_app.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld(){
        return helloWorldService.helloWorld(" Ernestogo");
    }

    @PostMapping
    public String helloworldPost(@RequestBody User body){
        return "hello world post" + body.getName();
    }
}
