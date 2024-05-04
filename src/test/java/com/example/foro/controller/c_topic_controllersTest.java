package com.example.foro.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.foro.controller.c_topic_controllers;
import com.example.foro.model.c_topic;
import com.example.foro.service.s_topic;
import com.example.foro.service.s_topicImpl;

@WebMvcTest(c_topic_controllers.class)
public class c_topic_controllersTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private s_topic topicServiceMock;

    @Test
    public void todosLosTopicos() throws Exception {

        // Arrange
        // Creacion de Topicos y sus mensajes
        c_topic topicA = new c_topic();
        topicA.setMensaje("Saludos desde Italia");
        topicA.setId(1L);
        c_topic topicB = new c_topic();
        topicB.setMensaje("Aquí en Puerto Rico");
        topicB.setId(2L);
        List<c_topic> Topic = Arrays.asList(topicA, topicB);
        when(topicServiceMock.getTopic()).thenReturn(Topic);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate:"/topic"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(expression:"$", Matchers.hasSize(size:2)))
                .andExpect(MockMvcResultMatchers.jsonPath(expression:"$[0].mensaje", Matchers.is(value:"Saludos desde Italia")))
                .andExpect(MockMvcResultMatchers.jsonPath(expression:"$[0].mensaje", Matchers.is(value:"Aqui en Puerto Rico")));

    }
}
