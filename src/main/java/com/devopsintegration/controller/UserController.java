package com.devopsintegration.controller;

import com.devopsintegration.dto.UserDto;
import com.devopsintegration.payload.ApiResponse;
import com.devopsintegration.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String id){
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserDto> userlist = userService.getAllUser();
        return new ResponseEntity<>(userlist, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String  id, @Valid @RequestBody UserDto userDto){
        userService.updateUser(id, userDto);
        return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
    }
}