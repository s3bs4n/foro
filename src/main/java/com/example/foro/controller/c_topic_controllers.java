package com.example.foro.controller;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foro.model.c_respuestas;
import com.example.foro.model.c_topic;
import com.example.foro.repository.r_respuestas;
import com.example.foro.repository.r_topic;
import com.example.foro.service.s_topic;

@RestController //para manejar solicitudes HTTP
// @RequestMapping("/topic")
public class c_topic_controllers {
    
    // @Autowired // como que vinvula o carga el repositorio en la clase, r_topic es el repositorio, se guarda en la variable repo_topic
    // private r_topic repo_topic; // r_topic es la clase principal

    @Autowired
    private r_respuestas repo_respuestas;
//
    // private static final Logger log = LoggerFactory.getLogger(c_topic_controllers.class);

    @Autowired
    private s_topic serv_topic;
    
// TOPICO

    @GetMapping("/topic") //se defite el metodo
    public CollectionModel<EntityModel<c_topic>> getTopic(){ //crea un procedimiento llamado getTopic, que va a devolver una lista de objetos "c_topic"
        List<c_topic> allTopic = serv_topic.getTopic(); // todo lo que es repositorio
        // log.info("GET /publicaciones");
        // log.info("Retornando todas las publicaciones");
        List<EntityModel<c_topic>> topicsResources = allTopic.stream()
            .map( topic -> EntityModel.of(topic,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getTopicByID((int) topic.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getTopic());
        CollectionModel<EntityModel<c_topic>> resources = CollectionModel.of(topicsResources, linkTo.withRel("topics"));

        return resources; // devuelve todos los topicos de la BDD
    }

    @GetMapping("/topic/{id_topico}")
    public ResponseEntity<Object> getTopicByID(@PathVariable int id_topico) { // crea el metodo getTopicbyID, usa el ID como parametro
        Optional<c_topic> topicOptional = repo_topic.findById(id_topico); // se intenta buscar un topico por su ID
        if (!topicOptional.isPresent()) { //verifica si el topico no se encontró
            Map<String, String> respuesta = new HashMap<>(); //se configura la estructura del JSON, la variable y su valor, es lo que hace el Hashmap
            respuesta.put("mensaje", "El tema con el ID " + id_topico + " no fue encontrado");
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);//devuelve el mensaje con un 404
        }

        c_topic topic = topicOptional.get(); //carga la clase topico
        return new ResponseEntity<>(topic, HttpStatus.OK); //devuelve la respuesta http con el topico encontrado
    }

	@PostMapping("/topic")
	public ResponseEntity<Object> ingresoTopic( @RequestBody c_topic topico ){ //se crea el metodo ingresoTopic, usa el formato de c_topic 
        Map<String, String> respuesta = new HashMap<String, String>(); // Se está creando una nueva instancia de Hashmap y se guarda en respuesta
        if(topico.getMensaje()==null || topico.getMensaje().trim() == ""){ // verifica si el mensaje es nulo o vacio
            respuesta.put("mensaje", "Ingrese Mensaje");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400)); //devuelve la respuesta HTTP con la variable respuesta
        }

        repo_topic.save(topico); // utiliza el repositorio para guardar el topico creado
        respuesta.put("topic", topico.getMensaje()); // agrega el mensade del topico al mapa
        respuesta.put("id", String.valueOf(topico.getId())); // agrega el ID del topico al mapa 
        return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(200)); //devuelve el mapa RESPUESTA con un 200
		
	}    
    
    @PutMapping("/topic/{id_topico}")
    public ResponseEntity<Object> actualizarTopic(@PathVariable int id_topico, @RequestBody c_topic topicoActualizado) { 
    
        Map<String, String> respuesta = new HashMap<String, String>();
        Optional<c_topic> topicOpt = repo_topic.findById(id_topico); 
    
        if (!topicOpt.isPresent()) {
            respuesta.put("mensaje", "El topico con ID " + id_topico + " no existe");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(404));
        }
    
        c_topic topicExistente = topicOpt.get();
        if (topicoActualizado.getMensaje() == null || topicoActualizado.getMensaje().trim().isEmpty()) {
            respuesta.put("mensaje", "Ingrese Mensaje");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400));
        }
    
        topicExistente.setMensaje(topicoActualizado.getMensaje());
        repo_topic.save(topicExistente);
    
        respuesta.put("mensaje", "Topico actualizado exitosamente");
        respuesta.put("topic", topicExistente.getMensaje());
        respuesta.put("id", String.valueOf(topicExistente.getId()));
        return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(200));
    }
    
    @DeleteMapping("/topic/{id_topico}")
	public ResponseEntity<Object> deleteTopic( @PathVariable int id_topico ){ 
        Map<String, String> respuesta = new HashMap<String, String>();
        
        Optional<c_topic> Topico = repo_topic.findById(id_topico);
        
        if(Topico.isEmpty()){
            respuesta.put("Mensaje", "Id del Tópico no existe");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400));
        }
        
        repo_topic.delete(Topico.get()); 
        
        respuesta.put("mensaje", "Tópico borrado con ID : " + String.valueOf( id_topico ) );
        return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(200)); 
	}        
    
    
    // RESPUESTA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/respuesta/{id_topico}/{id_respuesta}")
    public ResponseEntity<Object> obtenerRespuesta(@PathVariable int id_topico,
                                                    @PathVariable int id_respuesta) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<c_topic> optionalTopico = repo_topic.findById(id_topico);
        
        if(optionalTopico.isEmpty()) {
            respuesta.put("Mensaje", "El ID del Tópico no existe");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    
        c_topic topico = optionalTopico.get();
        List<c_respuestas> respuestas = topico.getRespuestas();
    
        // Buscar la respuesta por su ID
        Optional<c_respuestas> optionalRespuesta = respuestas.stream()
                                                            .filter(r -> r.getId() == id_respuesta)
                                                            .findFirst();
    
        if(optionalRespuesta.isEmpty()) {
            respuesta.put("Mensaje", "La respuesta no existe en este tópico");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    
        c_respuestas respuestaExistente = optionalRespuesta.get();
        
        // Agregar la respuesta a la respuesta del servicio
        respuesta.put("Mensaje", respuestaExistente.getMensaje());
        respuesta.put("Calificacion", respuestaExistente.getCalificacion());
        respuesta.put("ID_Tópico", id_topico);
        respuesta.put("ID_Respuesta", id_respuesta);
    
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @PostMapping("/respuesta/{id_topico}")
    public ResponseEntity<Object> ingresoRespuesta(@RequestBody c_respuestas comentario, @PathVariable int id_topico) {
        Map<String, String> respuesta = new HashMap<String, String>(); 
        Optional<c_topic> Topico = repo_topic.findById(id_topico); 
        if(Topico.isEmpty()){
            respuesta.put("Mensaje", "Id del Tópico no existe");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400)); 
        }

        if(comentario.getMensaje()==null || comentario.getMensaje().trim().length() == 0){
            respuesta.put("Mensaje", "Ingrese Respuesta");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400));            
        }

        if(comentario.getCalificacion() <=0 || comentario.getCalificacion()>7){
            respuesta.put("Mensaje", "Las Calificaciones deben ser entre 1 a 7");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400));                        
        }

        Topico.get().getRespuestas().add(comentario);
        repo_topic.save(Topico.get());
        
        respuesta.put("respuesta", comentario.getMensaje());
        respuesta.put("calificacion", String.valueOf(comentario.getCalificacion()));
        return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(200));
    }

    @PutMapping("/respuesta/{id_topico}/{id_respuesta}")
    public ResponseEntity<Object> actualizarRespuesta(@RequestBody c_respuestas comentario, 
                                                    @PathVariable int id_topico,
                                                    @PathVariable int id_respuesta) {
        Map<String, String> respuesta = new HashMap<>();
        Optional<c_topic> optionalTopico = repo_topic.findById(id_topico);
        
        if(optionalTopico.isEmpty()) {
            respuesta.put("Mensaje", "El ID del Tópico no existe");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        c_topic topico = optionalTopico.get();
        List<c_respuestas> respuestas = topico.getRespuestas();

        // Buscar la respuesta a actualizar por su ID
        Optional<c_respuestas> optionalRespuesta = respuestas.stream()
                                                            .filter(r -> r.getId() == id_respuesta)
                                                            .findFirst();

        if(optionalRespuesta.isEmpty()) {
            respuesta.put("Mensaje", "La respuesta a actualizar no existe en este tópico");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        c_respuestas respuestaExistente = optionalRespuesta.get();
        
        // Actualizar los campos de la respuesta
        if(comentario.getMensaje() != null && comentario.getMensaje().trim().length() > 0) {
            respuestaExistente.setMensaje(comentario.getMensaje());
        }

        if(comentario.getCalificacion() > 0 && comentario.getCalificacion() <= 7) {
            respuestaExistente.setCalificacion(comentario.getCalificacion());
        } else {
            respuesta.put("Mensaje", "Las calificaciones deben estar entre 1 y 7");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        // Guardar los cambios en la base de datos
        repo_topic.save(topico);

        respuesta.put("Mensaje", "Respuesta actualizada correctamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @DeleteMapping("/respuesta/{id_respuesta}")
	public ResponseEntity<Object> deleteRespuesta( @PathVariable int id_respuesta ){ 
        Map<String, String> respuesta = new HashMap<String, String>();

        Optional<c_respuestas> Respuesta = repo_respuestas.findById(id_respuesta);

        if(Respuesta.isEmpty()){
            respuesta.put("Mensaje", "Id del Respuesta no existe");
            return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(400));
        }

        repo_respuestas.delete( Respuesta.get() );

        respuesta.put("mensaje", "Respuesta borrada con ID : " + String.valueOf( id_respuesta ) );
        return new ResponseEntity<Object>(respuesta, HttpStatus.valueOf(200));
	}            

}
