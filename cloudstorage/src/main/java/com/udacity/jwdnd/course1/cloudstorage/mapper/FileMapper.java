package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getAllFilesByUserID(Integer userid);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFileByFileName(String filename);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    File getFileByFileNameAndUserID(String filename, Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFileInDB(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteFileByFileID(Integer fileid);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File getFileByFileID(Integer fileid);
}