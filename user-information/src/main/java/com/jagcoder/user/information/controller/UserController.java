package com.jagcoder.user.information.controller;


import com.jagcoder.user.information.dto.UserDTO;
import com.jagcoder.user.information.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/addUser",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/fetchUserById/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> fetchUserDetailsById(@PathVariable Integer userId){
        return userService.fetchUserDetailsById(userId);
    }



}
