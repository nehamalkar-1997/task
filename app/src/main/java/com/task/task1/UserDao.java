package com.task.task1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity insertUser);

    @Update
    public void updateUser(UserEntity updateUser);

    @Query("select * from user")
    List<UserEntity> getAllData();


    @Query ("SELECT * from user where name like :name and password like :password")
    UserEntity login(String name, String password);

    @Delete
    public void deleteUser(UserEntity user);

    @Query("select * from user where id like :id")
    UserEntity getUser(int id);
}
