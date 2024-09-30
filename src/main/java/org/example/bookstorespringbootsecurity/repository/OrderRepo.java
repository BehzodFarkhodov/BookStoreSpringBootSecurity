package org.example.bookstorespringbootsecurity.repository;

import org.example.bookstorespringbootsecurity.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> getAllByOwnerId(UUID ownerId);
}
