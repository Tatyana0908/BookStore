package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orderb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderb_id")
    private Integer orderId;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private Integer totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToMany
    @JoinTable(name = "book_order",
            joinColumns = @JoinColumn(name = "orderb_id", referencedColumnName = "orderb_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"))
    private List<Book> books;
}

