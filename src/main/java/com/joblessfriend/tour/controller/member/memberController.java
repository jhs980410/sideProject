package com.joblessfriend.tour.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class memberController {


    @GetMapping("/login")
    public String login() {
        return "member/loginForm";
    }
}
