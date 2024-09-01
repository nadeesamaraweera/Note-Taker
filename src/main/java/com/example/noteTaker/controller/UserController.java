package com.example.noteTaker.controller;


import com.example.noteTaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vi/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private  final UserService userService;

    //save user
     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE);
     public ResponseEntity<String> saveUser(
        @RequestPart("firstName") String  firstName,
        @RequestPart("lastName")String  lastName,
        @RequestPart("email")String  email,
        @RequestPart("password")String  password,
        @RequestPart("profilePic")String  profilePic) {

         //Handle Profile Picture


         return null;
     }

}
