package com.example.foro.service;

import com.example.foro.model.c_topic;
import com.example.foro.repository.r_topic;
import com.example.foro.service.s_topicImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import jakarta.inject.Inject;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class s_topicTest {

    @InjectMocks
    private s_topicImpl topicServicio;

    @Mock
    private r_topic topicRepositorioMock;

    
	@Test
	public void guardarTopicTest() {
        // Arrange
        c_topic topic = new c_topic();
        topic.setMensaje("Foto desde Service");

        when(topicRepositorioMock.save(any())).thenReturn(topic);

        // Act
        c_topic resultado = topicServicio.ingresoTopic(topic);
        
        // Assert
        assertEquals("Foto desde Service", resultado.getMensaje());

	}

}




