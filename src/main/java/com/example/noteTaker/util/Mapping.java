package com.example.noteTaker.util;

import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.entity.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping { //dto entity waltth entity dto wltth convert karanna.

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

}
