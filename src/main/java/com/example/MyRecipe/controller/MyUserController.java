package com.example.MyRecipe.controller;

import com.example.MyRecipe.model.MyUser;
import com.example.MyRecipe.service.MyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyUserController {

    MyUserService myUserService;

    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody MyUser myUser){
        if(myUserService.createUser(myUser)==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists: " + myUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(myUser.getUsername() + " has been created");
    }




}
