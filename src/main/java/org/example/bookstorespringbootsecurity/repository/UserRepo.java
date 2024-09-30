package org.example.bookstorespringbootsecurity.repository;

import org.example.bookstorespringbootsecurity.entity.UserEntity;
import org.example.bookstorespringbootsecurity.enumerator.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
   Boolean existsByUsername(String username);
   Optional<UserEntity> findByUsername(String username);
   List<UserEntity> getAllByRole(UserRole role);

}
