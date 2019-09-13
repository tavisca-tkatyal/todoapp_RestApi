package com.tavisca.todoapp;


import com.tavisca.todoapp.model.todo;
import com.tavisca.todoapp.service.todoservice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Hashtable;

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



}