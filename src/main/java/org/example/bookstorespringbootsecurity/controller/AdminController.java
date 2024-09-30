package org.example.bookstorespringbootsecurity.controller;

import org.example.bookstorespringbootsecurity.service.BookService;
import org.example.bookstorespringbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all-user")
    public String allUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/all-books")
    public String allBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "all-books";
    }
























}
