package com.example.noteTaker.service;

import com.example.noteTaker.dao.UserDAO;
import com.example.noteTaker.dto.UserDTO;
import com.example.noteTaker.entity.UserEntity;
import com.example.noteTaker.util.AppUtil;
import com.example.noteTaker.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        userDAO.save(mapping.convertToUserEntity(userDTO));
        return "User saved Successfully";

    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
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
}
