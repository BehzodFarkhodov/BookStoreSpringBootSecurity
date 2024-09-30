package org.example.bookstorespringbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alone")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "main-page";
    }

}
