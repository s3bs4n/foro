package com.example.foro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//para conexion directa a la tabla y los getters y setters automaticos
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="respuestas")

public class c_respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="topic_id", insertable = false, nullable = true)
    private int topic_id;
    
    @Column(name="mensaje") //nombre de la columna que se trae de la BDD
    private String mensaje;

    @Column(name="calificacion")
    private int calificacion;

}
