package com.vinder.vinderbackend.controllers;

import com.vinder.vinderbackend.models.user.Gender;
import com.vinder.vinderbackend.models.user.User;
import com.vinder.vinderbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.awt.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * GET /users
     * GET /users?gender=MALE
     */


    @GetMapping(value = "/users")
    public ResponseEntity<User> getAUser(
            @RequestParam(required = false, name = "gender") Gender gender
    ) {
        //returns all users by gender
        if (gender != null) {
            List<User> allOfGender = userRepository.findByGender(gender);
            return new ResponseEntity(allOfGender, HttpStatus.OK);
        }

        //returns all users
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}/potential-matches")
    public ResponseEntity<List<User>> getAllPotentialMatches(@PathVariable Long id) {
        List<User> users = userRepository.findAll();
        users.remove(id-1);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getAUser(@PathVariable Long id) {
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);
    }

//    @GetMapping(value = "/users/{id}/matches")
//    public ResponseEntity<List<User>> getAllUserMatches(@PathVariable Long id){
//        return new ResponseEntity<>()
//    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> putUser(@RequestBody User user, @PathVariable Long id) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}
