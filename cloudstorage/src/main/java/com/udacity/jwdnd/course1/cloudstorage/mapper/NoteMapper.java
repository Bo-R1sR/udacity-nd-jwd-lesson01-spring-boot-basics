package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNoteInDB(Note note);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note getNoteByNoteID(Integer noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getAllNotesByUserID(Integer userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void deleteNoteByNoteID(Integer noteid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
    int updateNoteInDB(Note note);
}