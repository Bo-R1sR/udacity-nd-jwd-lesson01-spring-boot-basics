package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredentialInDB(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getAllCredentialsByUserID(Integer userid);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialid=#{credentialid}")
    int updateCredentialInDB(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credential}")
    void deleteCredentialByCredentialID(Integer credentialid);
}