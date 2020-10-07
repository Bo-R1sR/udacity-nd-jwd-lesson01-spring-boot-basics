package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    private final JavascriptExecutor js;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "addNewNote")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "noteSubmitModal")
    private WebElement submitNoteModalButton;

    @FindBy(id = "successMsg")
    private WebElement successMsg;

    @FindBy(className = "notetitle")
    private WebElement noteTitle;

    @FindBy(className = "notedescription")
    private WebElement noteDescription;

    @FindBy(css = "#userTable .btn-success")
    private WebElement editButton;

    @FindBy(css = "#userTable .btn-danger")
    private WebElement deleteButton;

    @FindBy(id = "returnHome")
    private WebElement returnHome;

    public NotesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void insertNewNote(String noteTitle, String noteDescription) {
        js.executeScript("arguments[0].click();", notesTab);
        js.executeScript("arguments[0].click();", addNoteButton);
        js.executeScript("arguments[0].value='" + noteTitle + "';", noteTitleField);
        js.executeScript("arguments[0].value='" + noteDescription + "';", noteDescriptionField);
        js.executeScript("arguments[0].click();", submitNoteModalButton);
    }

    public void editNote(String noteTitle, String noteDescription) {
        js.executeScript("arguments[0].click();", notesTab);
        js.executeScript("arguments[0].click();", editButton);
        js.executeScript("arguments[0].value='" + noteTitle + "';", noteTitleField);
        js.executeScript("arguments[0].value='" + noteDescription + "';", noteDescriptionField);
        js.executeScript("arguments[0].click();", submitNoteModalButton);
    }

    public void deleteNote() {
        js.executeScript("arguments[0].click();", notesTab);
        js.executeScript("arguments[0].click();", deleteButton);
    }

    public String returnSuccessMsg() {
        return successMsg.getText();
    }

    public String getNoteTitle() {
        js.executeScript("arguments[0].click();", notesTab);
        return noteTitle.getText();
    }

    public String getNoteDescription() {
        js.executeScript("arguments[0].click();", notesTab);
        return noteDescription.getText();
    }

    public void returnHome() {
        js.executeScript("arguments[0].click();", returnHome);
    }


}
