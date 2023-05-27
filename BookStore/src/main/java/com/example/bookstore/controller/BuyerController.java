package com.example.bookstore.controller;

import com.example.bookstore.dto.BuyerDto;
import com.example.bookstore.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BuyerController {
    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    @GetMapping("/buyer")
    public List<BuyerDto> getAllBuyer() {
        return service.getAll();
    }

    @GetMapping("/buyer/{id}")
    public ResponseEntity<?> getBuyerById(@PathVariable Integer id) {
        Optional<BuyerDto> buyerOptional = service.getById(id);
        if (buyerOptional.isPresent()) {
            BuyerDto buyer = buyerOptional.get();
            return new ResponseEntity<>(buyer, HttpStatus.OK);
        } else {
            String errorMessage = "Покупатель не найден с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/buyer/delete/{id}")
    public ResponseEntity<?> deleteBuyer(@PathVariable Integer id) {
        try {
            service.deleteBuyer(id);
            return new ResponseEntity<>("Покупатель удален", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка удаления покупателя: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buyer/new")
    public void addBuyer(@RequestBody BuyerDto buyer) {
        service.addNewBuyer(buyer);
    }

    @PutMapping("/buyer/edit/{id}")
    public ResponseEntity<String> editBuyer(@RequestBody BuyerDto buyer, @PathVariable Integer id) {
        try {
            service.updateBuyer(buyer, id);
            return new ResponseEntity<>("Покупатель обновлен", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка обновления покупателя: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
