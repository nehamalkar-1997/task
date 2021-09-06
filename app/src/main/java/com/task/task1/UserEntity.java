package com.task.task1;

import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

@Entity(tableName = "user")
  public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "name")
    String name1;

    @ColumnInfo(name = "capturedImg")
    Bitmap bitmap;

    @ColumnInfo(name = "email")
    String email;



    public UserEntity(String password, String name1, String email) {
        this.password = password;
        this.name1 = name1;
        this.email = email;

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
