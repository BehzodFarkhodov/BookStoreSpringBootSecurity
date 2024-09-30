package org.example.bookstorespringbootsecurity.service;

import lombok.Setter;
import org.example.bookstorespringbootsecurity.domain.LoginDTO;
import org.example.bookstorespringbootsecurity.domain.UserCreatedDTO;
import org.example.bookstorespringbootsecurity.entity.UserEntity;
import org.example.bookstorespringbootsecurity.enumerator.UserRole;
import org.example.bookstorespringbootsecurity.exception.AuthException;
import org.example.bookstorespringbootsecurity.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void create(UserCreatedDTO userCreatedDTO) {
        if (userRepo.existsByUsername(userCreatedDTO.getUsername())) {
            throw new AuthException("Username already exists");
        }
        UserEntity user = modelMapper.map(userCreatedDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userCreatedDTO.getPassword()));
        userRepo.save(user);

    }

    public List<UserEntity> getAllUsers(){
        return userRepo.findAll().stream()
                .filter(user -> user.getRole() == UserRole.USER||user.getRole() == UserRole.SELLER)
                .collect(Collectors.toList());
    }

    public List<UserEntity> getAllUserByRole(String role){
       return userRepo.getAllByRole(UserRole.valueOf(role));
    }

    public void delete(UUID userId){
        userRepo.deleteById(userId);
    }


    public UserEntity findByUsername(String username){
      return   userRepo.findByUsername(username).orElseThrow(
                () -> new AuthException("Username not found")
        );
    }









}
