package com.example.user.Controller;

import com.example.user.DTO.Request.UserFiltersDTO;
import com.example.user.DTO.Request.UserRequestDTO;
import com.example.user.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * NO SE PUDO COMPLETAR SATISFACTORIAMENTE ESTE EJERCICIO DEL BONUS, Por falta de tiempo
     */

    /**======================
     * USERS CRUD ENDPOINTS
     **=====================*/

    @GetMapping("/user/{id}")
    public ResponseEntity get(@PathVariable Integer id){
        return this.service.get(id);
    }

    @PostMapping("/user")
    public ResponseEntity create(@Valid @RequestBody UserRequestDTO user){
        return this.service.create(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        return this.service.delete(id);
    }
    
    @PutMapping(value="/user/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody UserRequestDTO user) {
        return this.service.update(id, user);
    }

    /**==========
     * FILTERS
     **========*/

//    @RequestMapping("/users")
//    @GetMapping
//    public ResponseEntity getUsers(@RequestBody UserFiltersDTO filters){
//        return null;
//    }

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        return this.service.get();
    }

}
