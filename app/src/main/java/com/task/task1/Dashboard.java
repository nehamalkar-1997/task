package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {

    private ImageView imageView;
    private TextView heading;
    private TextView slogan;
    private Button bLogin;
    private Button bSingUp;
    private Button bUserList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageView = findViewById(R.id.imageView);
        heading = findViewById(R.id.heading);
        slogan = findViewById(R.id.slogan);
        bLogin = findViewById(R.id.bLogin);
        bSingUp = findViewById(R.id.bSignup);
        bUserList = findViewById(R.id.bUserList);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Login.class);
                startActivity(intent);
            }
        });
        bSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,SignUp.class);
                startActivity(intent);
            }
        });

        bUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,UserList.class);
                startActivity(intent);
            }
        });


    }
}