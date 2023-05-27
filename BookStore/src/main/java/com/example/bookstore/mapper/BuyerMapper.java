package com.example.bookstore.mapper;

import com.example.bookstore.dto.BuyerDto;
import com.example.bookstore.entity.Buyer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerMapper {
    BuyerDto toDto(Buyer entity);

    Buyer toEntity(BuyerDto dto);
}
