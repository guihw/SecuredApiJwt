package com.guilh.SecuredApiJWT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CustomerController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Authenticated user!";
    }
}
