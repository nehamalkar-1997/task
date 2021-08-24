package com.task.task1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//@TypeConverters(BitmapConvertor.class)
@Database(entities = {UserEntity.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {


    private static volatile UserDatabase instance;

    public static synchronized UserDatabase  getInstance(Context context){

        if (instance== null){
            instance = Room.databaseBuilder(context,UserDatabase.class,"UserDb" )
                     .build();
        }
        return instance;
    }
    public abstract UserDao getDao();
}
