package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.PostConstruct;

@Controller
@SessionAttributes(value = {"userid", "activeTab"})
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    String startTab;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostConstruct
    public void setStartTab() {
        startTab = "files";
    }

    @GetMapping("/home")
    public String showHomePage(Model model, Authentication authentication) {
        String username = authentication.getName();
        Integer userid = userService.getUserIDFromUserName(username);
        model.addAttribute("userid", userid);

        if (fileService.getAllFilesByUserID(userid).size() != 0)
            model.addAttribute("allFiles", fileService.getAllFilesByUserID(userid));

        if (credentialService.getAllCredentialsByUserID(userid).size() != 0)
            model.addAttribute("allCredentials", credentialService.getAllCredentialsByUserID(userid));

        if (noteService.getAllNotesByUserID(userid).size() != 0)
            model.addAttribute("allNotes", noteService.getAllNotesByUserID(userid));

        model.addAttribute("encryptionService", encryptionService);
        if (startTab != null) {
            model.addAttribute("activeTab", startTab);
            startTab = null;
        } else {
            String activeTab = (String) model.getAttribute("activeTab");
            if (activeTab != null) {
                model.addAttribute("activeTab", activeTab);
            } else {
                model.addAttribute("activeTab", "files");
            }
        }

        return "home";
    }
}
