package com.example.Image.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Image.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}