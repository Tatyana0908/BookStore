package com.example.bookstore.entity;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buyer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private Integer buyerId;

    @NotNull
    @Column(name = "first_name")
    private String first_name;

    @NotNull
    @Column(name = "second_name")
    private String second_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
}
