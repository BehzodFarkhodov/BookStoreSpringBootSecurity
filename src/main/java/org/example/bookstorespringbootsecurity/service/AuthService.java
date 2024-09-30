package org.example.bookstorespringbootsecurity.service;

import org.example.bookstorespringbootsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepo.findByUsername(username).orElseThrow(
              () -> new UsernameNotFoundException("User not found with username: " + username)
      );
    }
}
