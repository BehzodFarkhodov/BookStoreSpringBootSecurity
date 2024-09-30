package org.example.bookstorespringbootsecurity.controller;

import org.example.bookstorespringbootsecurity.domain.LoginDTO;
import org.example.bookstorespringbootsecurity.domain.UserCreatedDTO;
import org.example.bookstorespringbootsecurity.service.BookService;
import org.example.bookstorespringbootsecurity.service.OrderService;
import org.example.bookstorespringbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/register")
    public String openPage(){
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserCreatedDTO userCreatedDTO){
        userService.create(userCreatedDTO);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/user-delete/{userId}")
    public String delete(@ModelAttribute("userId")UUID userId,
                         Model model){
        userService.delete(userId);
        model.addAttribute("users",userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/all-book")
    public String allBooks(Model model, Principal principal){
        model.addAttribute("books",bookService.getAllBooks());
        model.addAttribute("orders",orderService.getAllOrders(principal));
        return "all-books-for-buy";
    }

    @GetMapping("/auth")
    public String page(){
        return "auth";
    }








}
