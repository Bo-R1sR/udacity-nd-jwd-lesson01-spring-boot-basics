package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"userid", "activeTab"})
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/insertOrUpdateCredentialInDB")
    public String insertOrUpdateCredentialInDB(Credential credential, Model model) {
        Integer userid = (Integer) model.getAttribute("userid");

        if (credential.getCredentialid() != null) {
            credentialService.updateCredentialInDB(credential);
        } else {
            credential.setUserid(userid);
            credentialService.insertCredentialInDB(credential);
        }

        model.addAttribute("activeTab", "credentials");
        model.addAttribute("successMessage", "Your credential was successfully saved. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        return "result";
    }

    @GetMapping("/deleteCredentialFromDB")
    public String deleteCredential(int credentialid, Model model) {
        credentialService.deleteCredentialByCredentialID(credentialid);
        model.addAttribute("activeTab", "credentials");
        model.addAttribute("successMessage", "Your credential was successfully deleted. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        return "result";
    }
}
