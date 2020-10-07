package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError(MultipartException e, Model model) {
        String errorMessage = "The file is to large:<p><i>" + e.getMessage() + "</i></p>Click <a id=\"returnHome\" href=\"home\">here</a> to continue.";
        model.addAttribute("errorMessage", errorMessage);
        return "result";
    }

}
