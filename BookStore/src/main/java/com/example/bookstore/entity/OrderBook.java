package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_order")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderb_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
