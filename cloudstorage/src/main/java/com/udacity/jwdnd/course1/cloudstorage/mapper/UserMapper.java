package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insertUserInDB(User user);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUserName(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    User getUserByUserID(Integer userid);

    @Select("SELECT userid FROM USERS WHERE username = #{username}")
    Integer getUserIDFromUserName(String username);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    void deleteUserByUserName(String username);
}
