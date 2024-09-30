package org.example.bookstorespringbootsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.bookstorespringbootsecurity.enumerator.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreatedDTO {
    private String name;
    private String username;
    private String password;
    private UserRole role;
}
