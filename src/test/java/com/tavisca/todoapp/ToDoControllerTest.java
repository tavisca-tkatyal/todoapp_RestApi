//package com.tavisca.todoapp;
//
//import com.tavisca.todoapp.controller.ToDoController;
//import com.tavisca.todoapp.model.todo;
//import com.tavisca.todoapp.service.todoservice;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Hashtable;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class ToDoControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private todoservice todorepo;
//    @InjectMocks
//    todo todo1;
//
//    @Before
//    public void beforeEachTest(){
//        todo1.setId(1);
//        todo1.setFirstName("Tapsi");
//    }
//    @Test
//    public void getAllTaskTest() throws Exception {
//        Hashtable<Integer,todo> item = new Hashtable<>();
//        item.put(1,todo1);
//        given()
//        JSONObject mockJSONResponse = new JSONObject()
//                .put("TaskList", taskList) ;
//        ResponseEntity responseEntity = new ResponseEntity<>(mockJSONResponse.toString(), HttpStatus.OK);
//        given(taskServices.getAllTask()).willReturn(responseEntity);
//        mvc.perform(MockMvcRequestBuilders
//                .get("/todos")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(responseEntity.getBody().toString()));
//    }
//}
//
//}
