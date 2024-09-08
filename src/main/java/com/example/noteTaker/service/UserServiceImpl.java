package com.example.noteTaker.service;

import com.example.noteTaker.dao.UserDAO;
import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.dto.UserDTO;
import com.example.noteTaker.entity.NoteEntity;
import com.example.noteTaker.entity.UserEntity;
import com.example.noteTaker.util.AppUtil;
import com.example.noteTaker.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private  final Mapping mapping;


    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity savedUser = userDAO.save(mapping.convertToUserEntity(userDTO));
        if (savedUser != null && savedUser.getUserId() != null){
            return "User saved Successfully";
        }else {
            return  "Save failed";
        }
    }

    @Override
    public boolean deleteUser(String userId) {

        if (userDAO.existsById(userId)) {
            userDAO.deleteById(userId);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntityByUserId = userDAO.getUserEntityByUserId(userId);
        return mapping.convertToUserDTO(userEntityByUserId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
      List<UserEntity> getAllUsers = userDAO.findAll();
      return  mapping.convertUserToDTOList(getAllUsers);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            return false;
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
        return true;
    }

}
