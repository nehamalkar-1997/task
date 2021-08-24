package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText userPassword;
    private Button btnLogin;
    private Button btnSingUp;
    UserDao userDatabase;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.ed_username);
        userPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login_in);
        btnSingUp = findViewById(R.id.btn_signup);

        userDatabase = UserDatabase.getInstance(this).getDao();

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String password = userPassword.getText().toString();


                if (name.equals("") && password.equals("")) {
                    Toast.makeText(Login.this, "fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            UserEntity userEntity = userDatabase.login(name, password);
                           if(userEntity==null)
                           {
                               new Handler(Looper.getMainLooper()).post(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Login.this, "user not found!", Toast.LENGTH_SHORT).show();
                                   }
                               });
                           }else
                           {
                               new Handler(Looper.myLooper().getMainLooper()).post(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Login.this, "Login SuccessFull" + name, Toast.LENGTH_SHORT).show();
                                   }
                               });

                           }

                        }
                    }).start();














            }}
        });


    }}
      /*
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String password = userPassword.getText().toString();
                Toast.makeText(Login.this, "hello world", Toast.LENGTH_SHORT).show();
                /*toast aa raha hai? nhi
                if (name.equals("") && password.equals("")) {
                    Toast.makeText(Login.this, "fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            UserEntity userEntity = userDatabase.login(name, password);
                           if(userEntity==null)
                           {
                               new Handler(Looper.getMainLooper()).post(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Login.this, "user not found!", Toast.LENGTH_SHORT).show();
                                   }
                               });
                           }else
                           {
                               new Handler(Looper.myLooper().getMainLooper()).post(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(Login.this, "Login succesfull" + name, Toast.LENGTH_SHORT).show();
                                   }
                               });

                           }

                        }
                    }).start();

                }
            }

            }
        });
    }
}
*/

/*btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

      /*  btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(Login.this,"Fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //perform query
                   UserDatabase userDatabase =UserDatabase.getUserDatabase(getApplicationContext());
                   final UserDao userDao = userDatabase.userDao();
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           UserEntity userEntity = userDao.login(username,password);
                           if (userEntity== null){
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_SHORT).show();
                                   }
                               });
                           }else{
                               String name = UserEntity.name;
                               startActivity(new Intent(Login.this,Dashboard.class).putExtra("name",name));
                           }
                       }
                   }).start();
                }

            }
        });

    }
}*/