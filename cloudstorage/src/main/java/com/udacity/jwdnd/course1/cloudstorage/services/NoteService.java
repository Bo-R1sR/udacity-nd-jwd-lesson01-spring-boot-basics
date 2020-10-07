package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int insertNoteInDB(Note note) {
        return noteMapper.insertNoteInDB(new Note(null, note.getNotetitle(), note.getNotedescription(), note.getUserid()));
    }

    public int updateNoteInDB(Note note) {
        return noteMapper.updateNoteInDB(note);
    }

    public List<Note> getAllNotesByUserID(Integer userid) {
        return noteMapper.getAllNotesByUserID(userid);
    }

    public void deleteNoteByNoteID(Integer noteid) {
        noteMapper.deleteNoteByNoteID(noteid);
    }

}
