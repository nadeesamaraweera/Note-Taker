package com.example.noteTaker.service;

import com.example.noteTaker.dao.NoteDAO;
import com.example.noteTaker.dto.NoteDTO;
import com.example.noteTaker.entity.NoteEntity;
import com.example.noteTaker.util.AppUtil;
import com.example.noteTaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Transactional
@Service // Component annotation eka meta anotate krla thinne service annotation eka athule
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
     private Mapping mapping;


//    List<NoteDTO> saveNoteTm = new ArrayList<>();
//    public NoteServiceImpl(){
//        saveNoteTm.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb",
//                "MadolDuwa","hiud","second","2024-08-25"));
//        saveNoteTm.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba66bb",
//                "MadolDuwa","hiud","second","2024-08-25"));
//        saveNoteTm.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba67bb",
//                "MadolDuwa","hiud","second","2024-08-25"));
//        saveNoteTm.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba68bb",
//                "MadolDuwa","hiud","second","2024-08-25"));
//        saveNoteTm.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba69bb",
//                "MadolDuwa","hiud","second","2024-08-25"));
//        System.out.println(saveNoteTm);
//    }


    @Override
    public String saveNote(NoteDTO noteDTO) {
//        noteDTO.setNoteId(AppUtil.createNote());
//        saveNoteTm.add(noteDTO);
//        return "Saved successfully in service layer";
        noteDTO.setNoteId(AppUtil.createNote());
        var noteEntity = mapping.convertToEntity(noteDTO);
        noteDAO.save(noteEntity);
        return "Saved successfully in service layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO incomeNoteDTO) {
     Optional<NoteEntity> tmpnoteEntity = noteDAO.findById(noteId);
     if(!tmpnoteEntity.isPresent()){
         return false;
     }else {
         tmpnoteEntity.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
         tmpnoteEntity.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
         tmpnoteEntity.get().setCreateDate(incomeNoteDTO.getCreateDate());
         tmpnoteEntity.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
     }
      return true;
    }

    @Override
    public boolean deleteNote(String noteId) {
        //noteDAO.deleteById(noteId);
        if(noteDAO.existsById(noteId)){
            noteDAO.deleteById(noteId);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        return mapping.convertToDTO(noteDAO.getReferenceById(noteId));

    }

    @Override
    public List<NoteDTO> getAllNotes() {
//       List<NoteEntity> getAllNotes = noteDAO.findAll();
//       List<NoteDTO> noteDTO= mapping.convertToDTO(getAllNotes)
//
//        return noteDTO;

       return mapping.convertToDTO(noteDAO.findAll());
    }
}
