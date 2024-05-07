package com.example.demo.dao;

import com.example.demo.entity.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:4200", "https://vacays-gchr-latest.onrender.com" })
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
