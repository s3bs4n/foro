// package com.example.foro.controller;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.hateoas.EntityModel;
// import org.springframework.test.web.servlet.MockMvc;

// import com.example.foro.controller.c_topic_controllers;
// import com.example.foro.model.c_topic;
// import com.example.foro.service.s_topic;
// import com.example.foro.service.s_topicImpl;

// @WebMvcTest(c_topic_controllers.class)
// public class c_topic_controllersTest {
    
//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private s_topic topicServiceMock;

//     @Test
//     public void todosLosTopicos() throws Exception {

//         c_topic topicA = new c_topic();
//         topicA.setMensaje("Saludos desde Italia");
//         topicA.setId(1);

//         c_topic topicB = new c_topic();
//         topicB.setMensaje("Aquí en Puerto Rico");
//         topicB.setId(2);

//         List<c_topic> Topic = List.of(topicA, topicB);
//         List<EntityModel<c_topic>> foroResources = Topic.stream()
//             .map(C_topic -> EntityModel.of(C_topic))
//             .collect(Collectors.toList());

//         when(topicServiceMock.getTopic()).thenReturn(Topic);

//         mockMvc.perform(get("/topic"))
//                 .andExpect(status().isOk())
//                 //En este caso, utilice la correspondencia directa de rutas JSON sin Matchers
//                 .andExpect(jsonPath("$._embedded.topic.length()").value(2))
//                 .andExpect(jsonPath("$._embedded.topic[0].mensaje").value("Saludos desde Italia"))
//                 .andExpect(jsonPath("$._embedded.topic[1].mensaje").value("Aquí en Puerto Rico"))
//                 .andExpect(jsonPath("$._embedded.topic[0]._links.self.href").value("http://localhost:8080/topic/1"))
//                 .andExpect(jsonPath("$._embedded.topic[1]._links.self.href").value("http://localhost:8080/topic/2"));
//     }
// }
