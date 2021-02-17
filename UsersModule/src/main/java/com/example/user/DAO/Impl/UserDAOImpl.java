package com.example.user.DAO.Impl;

import com.example.user.DAO.UserDAO;
import com.example.user.DTO.Request.UserRequestDTO;
import com.example.user.Exceptions.EmailNotUniqueException;
import com.example.user.Model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public User create(UserRequestDTO user) {
        List<User> users = this.readJson();
        User newUser = new User();
        if(isEmailUnique(users, user.getEmail())){
            newUser.setEmail(user.getEmail());
        } else {
            throw new EmailNotUniqueException("La direccion de email ya se encuentra registrada");
        }
        newUser.setId(Integer.valueOf(this.getLastUserId() + 1));
        newUser.setCity(user.getCity());
        newUser.setName(user.getName());
        newUser.setDni(user.getDni());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        users.add(newUser);
        this.writeJson(users);
        return newUser;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Integer id, UserRequestDTO user) {
        return null;
    }

    @Override
    public User get(Integer id) {
        return null;
    }

    @Override
    public List<User> get() {
        return this.readJson();
    }

    private Boolean isEmailUnique(List<User> users, String email){
        for(User u : users){
            if(u.getEmail().compareTo(email) == 0){
                return false;
            }
        }
        return true;
    }

    private Integer getLastUserId(){
        return this.readJson().stream().mapToInt(item -> item.getId()).max().orElseGet(() -> 0);
    }

    private void writeJson(List<User> users){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            objectMapper.writeValue(new ClassPathResource("static/users.json").getFile(), users);
        } catch (Exception e){
            throw new RuntimeException("Error writing JSON file" + e.getMessage());
        }
    }

    private List<User> readJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new ClassPathResource("static/users.json").getFile(), new TypeReference<List<User>>() {});
        } catch (Exception e){
            throw new RuntimeException("Error reading JSON file");
        }
    }
}
