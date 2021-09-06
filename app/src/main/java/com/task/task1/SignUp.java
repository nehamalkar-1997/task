package com.task.task1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SignUp extends AppCompatActivity {

    UserDatabase userDatabase;
    List<UserEntity> list;
    ImageButton captureImg;
    EditText username, userEmail, userPassword, confirmPassword;
    Button btnCreateUser, btnLogin, btnDate;
    TextView date;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userEmail = findViewById(R.id.ed_email);
        username = findViewById(R.id.ed_username);
        userPassword = findViewById(R.id.ed_password);
        confirmPassword = findViewById(R.id.ed_password2);
        btnCreateUser = findViewById(R.id.btn_create_user);
        btnLogin = findViewById(R.id.btnLogin);
        date= findViewById(R.id.tvDate);
        btnDate = findViewById(R.id.btn_Date);
        captureImg = findViewById(R.id.captureImg);
        userDatabase = UserDatabase.getInstance(getApplicationContext());


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginUser.class);
                startActivity(intent);

            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });
        captureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                if (name.isEmpty() && email.isEmpty() && password.isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                else
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //register user
                            UserEntity userEntity = new UserEntity(name, email, password);
                            userDatabase.getDao().registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
            }
        });
    }
    private void handleDateButton(){
        Calendar calendar = Calendar.getInstance();
        int Year= calendar.get(Calendar.YEAR);
        int Month=calendar.get(Calendar.MONTH);
        int Date=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             String dateString = year+ " / " +month+ " / " +dayOfMonth;
             date.setText(dateString);
            }
        },Year,Month,Date);
        datePickerDialog.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            Toast.makeText(getApplicationContext(), " Select Image", Toast.LENGTH_SHORT).show();
        } else {
            bitmap = (Bitmap) data.getExtras().get("data");
            captureImg.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


