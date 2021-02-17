package com.example.user.Service.Impl;

import com.example.user.DAO.UserDAO;
import com.example.user.DTO.Request.UserRequestDTO;
import com.example.user.DTO.Response.StatusResponseDTO;
import com.example.user.DTO.Response.UserResponseDTO;
import com.example.user.Exceptions.EmailNotUniqueException;
import com.example.user.Model.User;
import com.example.user.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO repository;

    @Autowired
    public UserServiceImpl(UserDAO repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity get() {
        List<User> users = this.repository.get();
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserResponseDTO> response = objectMapper.convertValue(users, new TypeReference<List<UserResponseDTO>>() {});
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity create(UserRequestDTO user) {
        HttpStatus status = null;
        ResponseEntity responseEntity;

        try{
            User userResponse = this.repository.create(user);
            ObjectMapper mapper = new ObjectMapper();
            UserResponseDTO response = mapper.convertValue(user, new TypeReference<UserResponseDTO>(){});
            status = HttpStatus.OK;
            responseEntity = new ResponseEntity(response, status);
        }catch (EmailNotUniqueException e){
            status = HttpStatus.BAD_REQUEST;
            StatusResponseDTO error = new StatusResponseDTO();
            error.setCode(status.value());
            error.setMessage(e.getMessage());
            responseEntity = new ResponseEntity(error, status);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity update(Integer id, UserRequestDTO user) {
        return null;
    }
}
