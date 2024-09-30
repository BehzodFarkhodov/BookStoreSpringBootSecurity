package org.example.bookstorespringbootsecurity.service;

import org.example.bookstorespringbootsecurity.domain.BookCreatedDTO;
import org.example.bookstorespringbootsecurity.entity.BookEntity;
import org.example.bookstorespringbootsecurity.entity.UserEntity;
import org.example.bookstorespringbootsecurity.exception.BaseException;
import org.example.bookstorespringbootsecurity.repository.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    public void save(BookCreatedDTO bookCreatedDTO, Principal principal) throws BaseException {
        if (bookRepo.existsByTitle(bookCreatedDTO.getTitle())) {
            throw new BaseException("Title already exists");
        }
        UserEntity user = userService.findByUsername(principal.getName());
        bookCreatedDTO.setSellerId(user.getId());
        bookRepo.save(modelMapper.map(bookCreatedDTO, BookEntity.class));
    }


    public void delete(UUID bookId){
        bookRepo.deleteById(bookId);
    }

    public List<BookEntity> getAllBooks(){
        return bookRepo.findAll();
    }

    public List<BookEntity> getAllBooksBySellerId(Principal principal){
        UserEntity user = userService.findByUsername(principal.getName());
        return bookRepo.getAllBySellerId(user.getId());
    }

    public BookEntity updateBook(BookEntity book, Principal principal) throws BaseException {
        Optional<BookEntity> books = bookRepo.findById(book.getId());
        UserEntity user = userService.findByUsername(principal.getName());
        books.get().setTitle(book.getTitle());
        books.get().setAuthor(book.getAuthor());
        books.get().setPrice(book.getPrice());
        books.get().setName(book.getName());
       books.get().setPage(book.getPage());
       books.get().setSeller(user);
        BookEntity saved = bookRepo.save(books.get());
        return saved;
    }










}
