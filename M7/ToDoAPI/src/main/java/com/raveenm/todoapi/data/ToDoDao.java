/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.todoapi.data;

import com.raveenm.todoapi.models.ToDo;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface ToDoDao {
    
    ToDo add(ToDo todo);
    
    List<ToDo> getAll();
    
    ToDo findById(int id);
    
    //true if item exists and is updated
    boolean update(ToDo todo);
    
    boolean deleteById(int id);
}
