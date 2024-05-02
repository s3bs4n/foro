package com.example.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foro.model.c_topic;

// toda esta clase corresponde a la tabla en la base de datos
// Ya sean Consultas, ingresos y update

@Repository
public interface r_topic extends JpaRepository<c_topic, Integer>{
    
}
