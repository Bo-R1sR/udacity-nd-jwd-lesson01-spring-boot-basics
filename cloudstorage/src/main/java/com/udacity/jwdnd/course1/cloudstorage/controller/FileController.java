package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes(value = {"userid", "activeTab"})
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadFileToDB")
    public String insertFileInDB(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) {
        Integer userid = (Integer) model.getAttribute("userid");
        if (fileUpload.isEmpty())
            model.addAttribute("errorMessage", "Please specify a file. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        else if (fileService.isFilenameAlreadyStored(fileUpload.getOriginalFilename(), userid))
            model.addAttribute("errorMessage", "Filename already exists. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        else {
            try {
                fileService.insertFileInDB(fileUpload, userid);
                model.addAttribute("successMessage", "Your file was successfully saved. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
            } catch (Exception e) {
                String errorMessage = "There was something wrong during the file upload:<p><i>" + e.getMessage() + "</i></p>Click <a id=\"returnHome\" href=\"home\">here</a> to continue.";
                model.addAttribute("errorMessage", errorMessage);
                return "result";
            }
        }
        model.addAttribute("activeTab", "files");
        return "result";
    }

    @GetMapping("/deleteFileFromDB")
    public String deleteFile(int fileid, Model model) {
        fileService.deleteFileByFileID(fileid);
        model.addAttribute("activeTab", "files");
        model.addAttribute("successMessage", "Your file was successfully deleted. Click <a id=\"returnHome\" href=\"home\">here</a> to continue.");
        return "result";
    }

    @GetMapping("/viewFileFromDB")
    public ResponseEntity download(int fileid) {
        File newFile = fileService.getFileByFileID(fileid);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(newFile.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + newFile.getFilename() + "\"")
                .body(newFile.getFiledata());
    }
}
