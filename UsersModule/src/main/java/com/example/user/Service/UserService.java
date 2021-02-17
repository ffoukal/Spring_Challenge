package com.example.user.Service;

import com.example.user.DTO.Request.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity get ();

    ResponseEntity get(Integer id);

    ResponseEntity create(UserRequestDTO user);

    ResponseEntity delete(Integer id);

    ResponseEntity update(Integer id, UserRequestDTO user);
}
