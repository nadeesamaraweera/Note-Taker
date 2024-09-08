package com.example.noteTaker.controller;


import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.dto.UserDTO;
import com.example.noteTaker.service.UserService;
import com.example.noteTaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vi/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    //save user
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //consume - clientge peththe
    //produce - server ek visin
    public ResponseEntity<String>

    saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic) {

        //Handle Profile Picture
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);

        //build the object
        var builduserDTO = new UserDTO();
        builduserDTO.setFirstName(firstName);
        builduserDTO.setLastName(lastName);
        builduserDTO.setEmail(email);
        builduserDTO.setPassword(password);
        builduserDTO.setProfilePic(base64ProfilePic);

        //send to the service layer

        return new ResponseEntity<>(userService.saveUser(builduserDTO), HttpStatus.CREATED);
    }

     //delete user
     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){
       return userService.deleteUser(userId)?new ResponseEntity<>(HttpStatus.NO_CONTENT):new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }
}

