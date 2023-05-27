package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.PublishingHouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "category_id", expression = "java(categoryIdFromCategory(book.getCategory()))")
    @Mapping(target = "publishing_house_id", expression = "java(publishingHouseIdFromCategory(book.getPublishingHouse()))")
    BookDto toDto(Book book);
    Book toEntity(BookDto dto);

    default Integer categoryIdFromCategory(Category category) {
        return category == null ? null : category.getCategoryId();
    }

    default Integer publishingHouseIdFromCategory(PublishingHouse publishingHouse) {
        return publishingHouse == null ? null : publishingHouse.getPublishingHouseId();
    }
}
