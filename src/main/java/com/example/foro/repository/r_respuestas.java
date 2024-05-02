package com.example.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foro.model.c_respuestas;

@Repository
public interface r_respuestas extends JpaRepository<c_respuestas, Integer>{
    //acá va toda la logica como los select, por el JPA
}
