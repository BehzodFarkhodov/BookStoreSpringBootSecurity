package org.example.bookstorespringbootsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books")
@Entity
public class BookEntity extends BaseEntity {
    private String name;
    @Column(unique = true)
    private String title;
    private String author;
    private Integer page;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;


}
