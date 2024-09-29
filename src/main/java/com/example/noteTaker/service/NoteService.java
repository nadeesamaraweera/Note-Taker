package com.example.noteTaker.service;

import com.example.noteTaker.customObj.NoteErrorResponse;
import com.example.noteTaker.customObj.NoteResponse;
import com.example.noteTaker.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
