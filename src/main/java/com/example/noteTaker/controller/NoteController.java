package com.example.noteTaker.controller;

import com.example.noteTaker.exception.NoteNotFound;
import com.example.noteTaker.service.NoteService;
import com.example.noteTaker.dto.impl.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vi/note")
@RequiredArgsConstructor
public class NoteController {

    @Autowired
    private final NoteService noteService;


    @GetMapping("health")
    public String healthCheck(){
        return "Note Taker is rnning";
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO note) {

        var saveData = noteService.saveNote(note);
        return ResponseEntity.ok(saveData);
    }

    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }


    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        return noteService.getSelectedNote(noteId);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        try {
            noteService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value ="/{noteId}" )
    public ResponseEntity<String> deleteNote(@PathVariable ("noteId") String noteId) {
       return noteService.deleteNote(noteId)?new ResponseEntity<>(HttpStatus.NO_CONTENT):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

//ResponseEntity<String> - usert (indetail)massage ekth ekkma states ekk ywann one nm me type eke object ekk use krai.