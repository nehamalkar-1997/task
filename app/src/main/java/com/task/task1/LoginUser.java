package com.task.task1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginUser extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnSingUp;
    UserDao userDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username =(EditText) findViewById(R.id.ed_username);
        password = ( EditText) findViewById(R.id.ed_password);
        btnLogin =(Button) findViewById(R.id.btn_login_in);
        btnSingUp = (Button) findViewById(R.id.btn_signup);

        userDatabase = UserDatabase.getInstance(this).getDao();

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginUser.this, SignUp.class);
                startActivity(intent);
            }
        });

     btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name = username.getText().toString();
            String userPassword = password.getText().toString();


            if (name.equals("") && password.equals("")) {
                Toast.makeText(LoginUser.this, "fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = userDatabase.login(name, userPassword);
                        if(userEntity==null)
                        {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginUser.this, "user not found!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else
                        {
                            new Handler(Looper.myLooper().getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {

                                    editor.putString("logged_user",userEntity.getName1());
                                    editor.putString("email",userEntity.getEmail());
                                    editor.putInt("id", userEntity.getId());
                                    editor.apply();
                                    Toast.makeText(LoginUser.this, "Login SuccessFull" + name, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();

            }
        }
     });
    }
}
