package com.jagcoder.user.information.service;


import com.jagcoder.user.information.dto.UserDTO;
import com.jagcoder.user.information.entity.User;
import com.jagcoder.user.information.mapper.UserMapper;
import com.jagcoder.user.information.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDTO addUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);

    }

    public UserDTO fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser = userRepo.findById(userId);
        return fetchedUser.map(UserMapper.INSTANCE::mapUserToUserDTO).orElse(null);
    }

}
