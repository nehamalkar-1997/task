package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView  name, email;
    private ImageView profileIv;
    Button  logoutBtn;
    UserDao database;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Bitmap bitmap;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.nameTv);
        email = findViewById(R.id.emailTv);
        profileIv = findViewById(R.id.profileIv);
        logoutBtn = findViewById(R.id.logoutBtn);
        database=UserDatabase.getInstance(getApplicationContext()).getDao();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        id= sharedPreferences.getInt("id",id);
        editor=sharedPreferences.edit();


        new Thread(new Runnable() {
            @Override
            public void run() {
              //  bitmap=database.getprofileIv(id);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        profileIv.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();


        String userName=sharedPreferences.getString("logged_user",null);
        String emailTxt=sharedPreferences.getString("logged_email",null);

       name.setText(userName);
       email.setText(emailTxt);



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* SessionManagement sessionManagement=new SessionManagement(getContext());
                sessionManagement.removeSession();

                moveToLogin();*/
                editor.clear();
                editor.apply();

            }

        });
    }
}

