package com.example.foro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foro.model.c_topic;
import com.example.foro.repository.r_topic;

import java.util.List;
import java.util.Optional;

@Service
public class s_topicImpl implements s_topic{
    @Autowired
    private r_topic repo_topic;

    @Override
    public List<c_topic> getTopic() {
        return repo_topic.findAll();
    }

    @Override
    public Optional<c_topic> getTopicByID(int id_topico) {
        return repo_topic.findById(id);
    }
    
    @Override
    public c_topic ingresoTopic(c_topic topico){
        return repo_topic.save(student);
    }

    @Override
    public c_topic actualizarTopic(int id_topico, c_topic topicoActualizado){
        if(repo_topic.existsById(id_topico)){
            topicoActualizado.setId(id_topico);
            return repo_topic.save(topicoActualizado);
        }   else {
                return null;
        }
    }

    @Override
    public void deleteTopic(int id_topico){
        repo_topic.deleteById(id_topico);
    }
}
