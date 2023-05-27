package com.example.bookstore.controller;

import com.example.bookstore.dto.PublishingHouseDto;
import com.example.bookstore.service.PublishingHouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublishingHouseController {
    private final PublishingHouseService service;

    public PublishingHouseController(PublishingHouseService service) {
        this.service = service;
    }

    @GetMapping("/publishing_house")
    public List<PublishingHouseDto> getAllPublishingHouse() {
        return service.getAll();
    }

    @GetMapping("/publishing_house/{id}")
    public ResponseEntity<?> getPublishingHouseById(@PathVariable Integer id) {
        Optional<PublishingHouseDto> publishingHouseOptional = service.getById(id);
        if (publishingHouseOptional.isPresent()) {
            PublishingHouseDto publishingHouse = publishingHouseOptional.get();
            return new ResponseEntity<>(publishingHouse, HttpStatus.OK);
        } else {
            String errorMessage = "Издание не найдено с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/publishing_house/delete/{id}")
    public ResponseEntity<String> deletePublishingHouse(@PathVariable Integer id) {
        try {
            service.deletePublishingHouse(id);
            return new ResponseEntity<>("Издание удалено", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка удаления издания: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/publishing_house/new")
    public void addPublishingHouse(@RequestBody PublishingHouseDto publishingHouse) {
        service.addNewPublishingHouse(publishingHouse);
    }

    @PutMapping("/publishing_house/edit/{id}")
    public ResponseEntity<String> editPublishingHouse(@RequestBody PublishingHouseDto publishingHouse, @PathVariable Integer id) {
        try {
            service.updatePublishingHouse(publishingHouse, id);
            return new ResponseEntity<>("Издание обновлено", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка обновления издания: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
