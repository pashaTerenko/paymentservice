package com.terenko.paymentservice.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
