package com.example.noteTaker.controller;


import com.example.noteTaker.dao.UserDAO;
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

import java.util.List;

@RestController
@RequestMapping("api/vi/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserDAO userDAO;


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

        var saveStatus = userService.saveUser(builduserDTO);
        if (saveStatus.contains("User saved successfully")){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){
       return userService.deleteUser(userId)?new ResponseEntity<>(HttpStatus.NO_CONTENT):new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("id") String id,
                                             @RequestPart("updateFirstName") String updateFirstName,
                                             @RequestPart("updateLastName") String updateLastName,
                                             @RequestPart("updateEmail") String updateEmail,
                                             @RequestPart("updatePassword") String updatePassword,
                                             @RequestPart("updateProfilePic") String updateProfilePic)     {
       String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
       var updateUser = new UserDTO();
        updateUser.setUserId(id);
        updateUser.setFirstName(updateFirstName);
        updateUser.setLastName(updateLastName);
        updateUser.setEmail(updateEmail);
        updateUser.setPassword(updatePassword);
        updateUser.setProfilePic(updateBase64ProfilePic);
        return userService.updateUser(updateUser)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

