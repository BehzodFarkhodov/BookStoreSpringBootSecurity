package org.example.bookstorespringbootsecurity.controller;

import org.example.bookstorespringbootsecurity.domain.BookCreatedDTO;
import org.example.bookstorespringbootsecurity.entity.BookEntity;
import org.example.bookstorespringbootsecurity.entity.UserEntity;
import org.example.bookstorespringbootsecurity.repository.BookRepo;
import org.example.bookstorespringbootsecurity.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/book")

public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookService bookService;

    @GetMapping("/create-book")
    public String createBook(Model model,Principal principal) {
        model.addAttribute("books",bookService.getAllBooksBySellerId(principal));
        return "create-book";
    }

    @PostMapping("/create-book")
    public String addBook(@ModelAttribute BookCreatedDTO bookCreatedDTO,
                          Principal principal,Model model) {
        bookService.save(bookCreatedDTO,principal);
        model.addAttribute("books",bookService.getAllBooksBySellerId(principal));

        return "create-book";
    }

    @PostMapping("/book-delete/{bookId}")
    public String deleteBook(@ModelAttribute("bookId") UUID bookId,Model model,Principal principal){
        bookService.delete(bookId);
        model.addAttribute("books",bookService.getAllBooksBySellerId(principal));
        return "create-book";
    }




    @GetMapping("/book-update/{bookId}")
    public String updateBook(@ModelAttribute("bookId") UUID bookId) {
        return "update-book";
    }


    @PostMapping("/book-update/{bookId}")
    public String update(@ModelAttribute("bookId") UUID bookId,
                         Model model,Principal principal){
        Optional<BookEntity> book = bookRepo.findById(bookId);
        bookService.updateBook(book.get(),principal);
        model.addAttribute("books",bookService.getAllBooksBySellerId(principal));
        return "create-book";
    }























}
