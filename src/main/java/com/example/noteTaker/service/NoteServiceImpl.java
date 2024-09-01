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
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
//        ListIterator<NoteDTO> updatedList = saveNoteTm.listIterator();
//        while (updatedList.hasNext()) {
//            NoteDTO noteDTO = updatedList.next();
//            if (noteId.equals(noteDTO.getNoteId())) {
//                incomeNoteDTO.setNoteId(noteDTO.getNoteId());
//                updatedList.set(incomeNoteDTO);
//                break;
//            }
//        }
    }

    @Override
    public void deleteNote(String noteId) {
//        ListIterator<NoteDTO> tmpList = saveNoteTm.listIterator();
//        while (tmpList.hasNext()) {
//            NoteDTO noteDTO = tmpList.next();
//            if (noteId.equals(noteDTO.getNoteId())) {
//                tmpList.remove();
//            }
//        }

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
