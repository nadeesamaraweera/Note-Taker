package com.example.noteTaker.service;

import com.example.noteTaker.customObj.UserErrorResponse;
import com.example.noteTaker.customObj.UserResponse;
import com.example.noteTaker.dao.UserDAO;
import com.example.noteTaker.dto.impl.UserDTO;
import com.example.noteTaker.entity.UserEntity;
import com.example.noteTaker.exception.UserNotFoundException;
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
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            throw  new UserNotFoundException("User Not Found");
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
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
        public UserResponse getSelectedUser(String userId) {
            if(userDAO.existsById(userId)){
                UserEntity userEntityByUserId = userDAO.getUserEntityByUserId(userId);
                return mapping.convertToUserDTO(userEntityByUserId);
            }else {
                return new UserErrorResponse(0, "User not found");
            }
        }


    @Override
    public List<UserDTO> getAllUsers() {
      List<UserEntity> getAllUsers = userDAO.findAll();
      return  mapping.convertUserToDTOList(getAllUsers);
    }


}
