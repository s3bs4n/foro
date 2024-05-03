package com.example.foro.service;

import com.example.foro.model.c_topic;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface s_topic {
    List<c_topic> getTopic();
    Optional<c_topic> getTopicByID(int id_topico);
    c_topic ingresoTopic(c_topic topico);
    c_topic actualizarTopic(int id_topico, c_topic topicoActualizado);
    void deleteTopic(int id_topico);

}