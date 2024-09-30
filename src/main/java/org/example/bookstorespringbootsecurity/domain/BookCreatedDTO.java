package org.example.bookstorespringbootsecurity.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreatedDTO {
    private String name;
    private String title;
    private String author;
    private Integer page;
    private Double price;
    private UUID sellerId;
}
