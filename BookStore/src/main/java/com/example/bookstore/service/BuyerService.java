package com.example.bookstore.service;

import com.example.bookstore.mapper.BuyerMapper;
import com.example.bookstore.repository.BuyerRepository;
import com.example.bookstore.dto.BuyerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuyerService {
    private final BuyerRepository repository;
    private final BuyerMapper mapper;

    public BuyerService(BuyerRepository repository, BuyerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BuyerDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<BuyerDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewBuyer(BuyerDto buyer) {
        repository.save(mapper.toEntity(buyer));
    }

    public void deleteBuyer(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Покупатель с id " + id + " не найдена");
        }
    }

    public void updateBuyer(BuyerDto buyer, Integer id) {
        repository.findById(id).ifPresentOrElse(entity -> {
            entity.setFirst_name(buyer.getFirst_name());
            entity.setSecond_name(buyer.getSecond_name());
            entity.setPhone(buyer.getPhone());
            entity.setEmail(buyer.getEmail());
            repository.save(entity);
        }, () -> {
            throw new IllegalArgumentException("Покупатель с id " + id + " не найден");
        });
    }


}
