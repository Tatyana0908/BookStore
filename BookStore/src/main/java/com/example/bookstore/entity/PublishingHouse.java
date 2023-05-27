package com.example.bookstore.entity;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "publishing_house")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_house_id")
    private Integer publishingHouseId;

    @NotNull
    @Column(name = "name")
    private String name;
}
