package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFilesByUserID(Integer userid) {
        return fileMapper.getAllFilesByUserID(userid);
    }

    public int insertFileInDB(MultipartFile fileUpload, Integer userid) throws IOException {
        return fileMapper.insertFileInDB(new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), String.valueOf(fileUpload.getSize()), userid, fileUpload.getBytes()));
    }
    public void deleteFileByFileID(Integer fileid) {
        fileMapper.deleteFileByFileID(fileid);
    }

    public boolean isFilenameAlreadyStored(String filename, Integer userid) {
        return fileMapper.getFileByFileNameAndUserID(filename, userid) != null;
    }

    public File getFileByFileID(Integer fileid) {
        return fileMapper.getFileByFileID(fileid);
    }




}
