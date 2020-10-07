package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"userid", "activeTab"})
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/insertOrUpdateNoteInDB")
    public String insertOrUpdateNoteInDB(Note note, Model model) {
        Integer userid = (Integer) model.getAttribute("userid");

        if (note.getNoteid() != null) {
            noteService.updateNoteInDB(note);
        } else {
            note.setUserid(userid);
            noteService.insertNoteInDB(note);
        }
        model.addAttribute("successMessage", "Your note was successfully saved. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        model.addAttribute("activeTab", "notes");

        return "result";
    }

    @GetMapping("/deleteNoteFromDB")
    public String deleteNote(int noteid, Model model) {
        noteService.deleteNoteByNoteID(noteid);
        model.addAttribute("activeTab", "notes");
        model.addAttribute("successMessage", "Your note was successfully deleted. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        return "result";
    }
}
