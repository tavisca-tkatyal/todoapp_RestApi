package com.tavisca.todoapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.todoapp.model.todo;
import com.tavisca.todoapp.service.todoservice;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Hashtable;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private todoservice todoRepo;


    @InjectMocks
    todo todo1;

    @Before
    public void setUp() {
        todo1.setId(1);
        todo1.setFirstName("Tapsi");
    }

    @Test
    public void getAllTodos() throws Exception {
        // given
        Hashtable<Integer, todo> item = new Hashtable<>();
        item.put(1, todo1);
        given(todoRepo.getAll()).willReturn(item);

        // when + then
        this.mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"1\": {\"id\": 1,\"firstName\": \"Tapsi\" } }"));
    }
     @Test
     public void getAnItemById() throws Exception {
         // given

         given(todoRepo.getItems(1)).willReturn(todo1);

         // when + then
         this.mockMvc.perform(get("/todos/1"))
                 .andExpect(status().isOk())
                 .andExpect(content().json
                         ("{\"id\": 1,\"firstName\": \"Tapsi\" }"));
     }

    @Test
    public void addTodoItem() throws Exception {
        // given

        todo1.setId(2);
        todo1.setFirstName("Ojas");
        Hashtable<Integer, todo> item = new Hashtable<>();
        item.put(2, todo1);
        given(todoRepo.addItem("Ojas")).willReturn(item);
        given(todoRepo.getItems(2)).willReturn(todo1);

        // when + then
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo1)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTodoItem() throws Exception {
        // given
        Hashtable<Integer,todo> item = new Hashtable<>();
        todo1.setId(1);
        todo1.setFirstName("Varsha");
        item.put(1,todo1);

        given(todoRepo.updateItem(1,"varsha")).willReturn(item);

        // when + then
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(put("/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo1)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTodoItem() throws Exception {

        todoservice todo =  Mockito.mock(todoservice.class);
        Hashtable<Integer,todo> someModelList = todo.getAll();
        Mockito.when(todoRepo.getAll()).thenReturn(someModelList);
        this.mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk());
    }

}