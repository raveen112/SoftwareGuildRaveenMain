/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.todoapi.controllers;

import com.raveenm.todoapi.data.ToDoDao;
import com.raveenm.todoapi.models.ToDo;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ravee
 */
@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoDao dao;

    public ToDoController(ToDoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ToDo> all() {
        return dao.getAll();
    }

    //Create functionality
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo create(@RequestBody ToDo todo) {
        return dao.add(todo);
    }

    // find by id 
    @GetMapping("/{id}")
    // ResponseEntity used to add flexibility of sending different statuses based on finding or not finding id
    // @PathVariable tells Spring MVC to expect a value, id, in the URL and convert it to an integer.
    public ResponseEntity<ToDo> findById(@PathVariable int id) {
        ToDo result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody ToDo todo) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != todo.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        } else if (dao.update(todo)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(dao.deleteById(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
