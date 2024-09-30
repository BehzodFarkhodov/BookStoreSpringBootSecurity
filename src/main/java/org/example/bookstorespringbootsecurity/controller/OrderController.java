package org.example.bookstorespringbootsecurity.controller;

import org.example.bookstorespringbootsecurity.domain.OrderCreatedDTO;
import org.example.bookstorespringbootsecurity.service.BookService;
import org.example.bookstorespringbootsecurity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;



   @GetMapping("/create-order/{bookId}")
   public String createOrderForm(@PathVariable ("bookId") UUID bookId, Model model,
                             Principal principal) {
       model.addAttribute("orders", orderService.getAllOrders(principal));
       model.addAttribute("bookId", bookId);
       return "all-books-for-buy";

   }


    @PostMapping("/create-order/{bookId}")
    public String createOrder(@PathVariable ("bookId") UUID bookId,
                              Model model,
                              @ModelAttribute OrderCreatedDTO dto,
                              Principal principal) {
        orderService.buy(dto, principal);
        model.addAttribute("orders", orderService.getAllOrders(principal));
        model.addAttribute("books", bookService.getAllBooks());
        return "all-books-for-buy";
    }
}

