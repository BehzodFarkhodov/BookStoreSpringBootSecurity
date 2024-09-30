package org.example.bookstorespringbootsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.dom.DOMCryptoContext;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@Entity
public class OrderEntity extends BaseEntity {
    private Double price;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;


}
