package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;

    private String author;

    private Double price;

    private Integer category_id;

    private Integer publishing_house_id;
}
