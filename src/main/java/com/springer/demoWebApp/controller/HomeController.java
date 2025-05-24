package com.springer.demoWebApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello world!\n" + request.getRequestedSessionId();
    }
}
