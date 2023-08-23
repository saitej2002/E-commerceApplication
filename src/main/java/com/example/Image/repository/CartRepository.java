package com.example.Image.repository;

import com.example.Image.entity.Cart;
import com.example.Image.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
  public List<Cart> findByUser(User user);
}
