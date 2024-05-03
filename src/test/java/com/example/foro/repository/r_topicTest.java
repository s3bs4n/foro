package com.example.foro.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.foro.model.c_topic;
// 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// toda esta clase corresponde a la tabla en la base de datos
// Ya sean Consultas, ingresos y update

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class r_topicTest {
    @Autowired
    private r_topic R_topic;

    @Test
    public void guardarTopicTest() {
        // Arrange
        c_topic topic = new c_topic();
        topic.setMensaje("Foto desde el Test");

        // Act
        c_topic resultado = R_topic.save(topic);
        
        // Assert
        assertNotNull(resultado.getId());
        assertEquals("Foto desde el Test", resultado.getMensaje());
    }

}