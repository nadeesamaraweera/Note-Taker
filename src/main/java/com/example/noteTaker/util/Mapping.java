package com.example.noteTaker.util;

import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.dto.UserDTO;
import com.example.noteTaker.entity.NoteEntity;
import com.example.noteTaker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping { //dto entity waltth entity dto wltth convert karanna.  //anith layers athre data gnne dto wlin.

    @Autowired
    private ModelMapper modelMapper;

    //matters of NoteEntity and DTO

    public NoteDTO convertToDTO(NoteEntity note){
        return modelMapper.map(note,NoteDTO.class);
    }

    public NoteEntity convertToEntity(NoteDTO dto){
        return modelMapper.map(dto, NoteEntity.class);
    }

    public List<NoteDTO> convertToDTO(List<NoteEntity> notes){
        return modelMapper.map(notes,List.class);
    }


    //user matters mapping

    public UserEntity convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> convertUserToDTO(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, List.class);
    }
}
