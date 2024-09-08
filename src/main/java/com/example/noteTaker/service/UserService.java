package com.example.noteTaker.service;

import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.dto.UserDTO;

import java.util.List;

public interface UserService {

    String saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);    boolean deleteUser(String userId);
    UserDTO getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
