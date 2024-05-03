package com.example.foro.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Crea los setter sin la necesidad de hacerlo manualmente
@NoArgsConstructor  // Crea los getter sin la necesidad de hacerlo manualmente
@Entity
@Table(name="topic") //nombre tabla

public class c_topic extends RepresentationModel<c_topic>{
    
    @Id //marca una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //asignable 1 vez y solo en el constructor

    @Column(name="mensaje")
    private String mensaje;

    @OneToMany(targetEntity=c_respuestas.class, cascade = CascadeType.ALL) // se pone que la entidad topico puede tener una o varias respuestas. El cascade, útil por si borro un topico, y asi borra las respuestas asociadas
    @JoinColumn(name="topic_id", referencedColumnName="id")    
    private List<c_respuestas> respuestas; // se manda a llamar a la clase respuestas, si llamo a un topico por ejemplo y tiene respuestas, las carga automaticamente

    public int getPromedio(){
        if(respuestas.size()>0){
            int totalCalificaciones = 0;
            for(c_respuestas respuesta_individual : respuestas){
                totalCalificaciones += respuesta_individual.getCalificacion();
            }
            return totalCalificaciones / respuestas.size();
        }
        return 0;
    }


}
