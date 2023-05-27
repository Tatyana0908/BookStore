package com.example.bookstore.mapper;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Buyer;
import com.example.bookstore.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "buyerId", expression = "java(buyerIdFromBuyer(order.getBuyer()))")
    @Mapping(target = "bookIds", expression = "java(bookIdsFromBooks(order.getBooks()))")
    OrderDto toDto(Order order);
    Order toEntity(OrderDto dto);

    default Integer buyerIdFromBuyer(Buyer buyer) {
        return buyer == null ? null : buyer.getBuyerId();
    }

    default List<Integer> bookIdsFromBooks(List<Book> books) {
        return books == null ? null : books.stream().map(Book::getBookId).collect(Collectors.toList());
    }
}
