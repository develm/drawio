package com.sql.er.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/signin")
    public String signIn(){
        return "signin";
    }

    @RequestMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @ResponseBody
    @RequestMapping("/signInSubmit")
    public String signInSubmit(){
        return "";
    }

    @ResponseBody
    @RequestMapping("/signUpSubmit")
    public String signUpSubmit(){
        return "";
    }
}
