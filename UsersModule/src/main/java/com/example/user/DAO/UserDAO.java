package com.example.user.DAO;

import com.example.user.DTO.Request.UserRequestDTO;
import com.example.user.Model.User;

import java.util.List;

public interface UserDAO {
    User create(UserRequestDTO user);
    Boolean delete(Integer id);
    Boolean update(Integer id, UserRequestDTO user);
    User get(Integer id);
    List<User> get();
}
