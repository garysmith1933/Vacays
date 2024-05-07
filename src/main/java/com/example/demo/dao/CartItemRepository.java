package com.example.demo.dao;

import com.example.demo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "http://localhost:4200", "https://vacays-gchr-latest.onrender.com" } )
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
