package com.example.bookstore.service;

import com.example.bookstore.repository.PublishingHouseRepository;
import com.example.bookstore.dto.PublishingHouseDto;
import com.example.bookstore.entity.PublishingHouse;
import com.example.bookstore.mapper.PublishingHouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublishingHouseService {
    private final PublishingHouseRepository repository;
    private final PublishingHouseMapper mapper;

    public PublishingHouseService(PublishingHouseRepository repository, PublishingHouseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PublishingHouseDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<PublishingHouseDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewPublishingHouse(PublishingHouseDto publishingHouse) {
        repository.save(mapper.toEntity(publishingHouse));
    }

    public void deletePublishingHouse(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Издание с id " + id + " не найдено");
        }
    }

    public void updatePublishingHouse(PublishingHouseDto publishingHouse, Integer id) {
        PublishingHouse entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Издание с id " + id + " не найдено"));
        entity.setName(publishingHouse.getName());
        repository.save(entity);
    }

}
