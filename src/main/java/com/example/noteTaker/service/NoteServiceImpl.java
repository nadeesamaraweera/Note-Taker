package com.example.noteTaker.service;

import com.example.noteTaker.dao.NoteDAO;
import com.example.noteTaker.dto.impl.NoteDTO;
import com.example.noteTaker.entity.NoteEntity;
import com.example.noteTaker.exception.DataPersistFailedException;
import com.example.noteTaker.exception.NoteNotFound;
import com.example.noteTaker.util.AppUtil;
import com.example.noteTaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service // Component annotation eka meta anotate krla thinne service annotation eka athule
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
     private Mapping mapping;


    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNote());
        var noteEntity = mapping.convertToEntity(noteDTO);
        var savedNoted = noteDAO.save(noteEntity);
        if(savedNoted == null){
            throw new DataPersistFailedException("Cannot save Note");
        }

    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
     Optional<NoteEntity> tmpnoteEntity = noteDAO.findById(noteId);
     if(!tmpnoteEntity.isPresent()){
         throw new NoteNotFound("Note Not Found");
     }else {
         tmpnoteEntity.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
         tmpnoteEntity.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
         tmpnoteEntity.get().setCreateDate(incomeNoteDTO.getCreateDate());
         tmpnoteEntity.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
     }
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
