package com.example.bookstore.mapper;
import com.example.bookstore.dto.PublishingHouseDto;
import com.example.bookstore.entity.PublishingHouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublishingHouseMapper {
    PublishingHouseDto toDto(PublishingHouse entity);
    PublishingHouse toEntity(PublishingHouseDto dto);
}
