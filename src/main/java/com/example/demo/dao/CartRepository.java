package com.example.demo.dao;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:4200", "https://vacays-gchr-latest.onrender.com" })
public interface CartRepository extends JpaRepository<Cart, Long> {
}
