package org.example.bookstorespringbootsecurity.repository;

import org.example.bookstorespringbootsecurity.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, UUID> {

    Boolean existsByTitle(String title);
    List<BookEntity> getAllBySellerId(UUID sellerId);
}
