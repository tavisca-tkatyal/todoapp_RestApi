package com.tavisca.todoapp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tavisca.todoapp.service.todoservice;
import com.tavisca.todoapp.model.todo;

import java.util.Hashtable;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ToDoController {
    @Autowired
    todoservice todo;

    @GetMapping("/todos")
    public ResponseEntity<Hashtable<Integer,todo>> getAll(){
        if(todo.getAll()!=null)
            return new ResponseEntity<>(todo.getAll(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/todos/{itemId}")
    public ResponseEntity<todo> getTodo(@PathVariable("itemId")int id) {
        if(todo.getItems(id)!=null)
            return new ResponseEntity<>(todo.getItems(id),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/todos")
    public Hashtable<Integer,todo> addItem(@RequestBody String json) {
      JSONObject obj = new JSONObject(json);
      String todoname = obj.getString("firstName");
        return todo.addItem(todoname);
    }

    @DeleteMapping("/todos/{id}")
    public Hashtable<Integer,todo> deleteItem(@PathVariable("id") int id){
        return todo.deleteItem(id);
    }

    @PutMapping("/todos/{id}")
    public Hashtable<Integer, com.tavisca.todoapp.model.todo> updateItem
            (@PathVariable("id") int id ,@RequestBody String json) {
        JSONObject obj = new JSONObject(json);
        String updatedValue = obj.getString("firstName");
        return todo.updateItem(id, updatedValue);
    }
}