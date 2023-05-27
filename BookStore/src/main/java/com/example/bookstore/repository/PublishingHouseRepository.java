package com.example.bookstore.repository;

import com.example.bookstore.entity.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Integer> {
}
