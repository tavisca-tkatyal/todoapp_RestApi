package com.tavisca.todoapp.service;
import com.tavisca.todoapp.model.todo;
import org.springframework.stereotype.Service;
import java.util.Hashtable;
@Service
public class todoservice {
    Hashtable<Integer,todo> item = new Hashtable<Integer, todo>();
    static int id=1;
    todo t = new todo();
    public todo getItems(int id)
    {
        if(item.containsKey(id)){
            return item.get(id);
        }
        else{
            return null;
        }
    }

    public Hashtable<Integer,todo> getAll()
    {
        return item;
    }

    public Hashtable<Integer,todo> addItem(String itemName) {
        t= new todo();
        t.setFirstName(itemName);
        t.setId(id);
        item.put(id++,t);
        return item;
    }
     public Hashtable<Integer,todo>deleteItem( int id) {
         if(item.containsKey(id)){
             item.remove(id);
         }
            return item;
     }

     public Hashtable<Integer, todo> updateItem(int id , String name){
            if(item.containsKey(id)){
                todo t = item.get(id);
                t.setFirstName(name);
            }
             return item;
     }

}