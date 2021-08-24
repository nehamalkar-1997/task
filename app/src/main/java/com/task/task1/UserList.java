package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    private UserListAdapter userListAdapter;
List<UserEntity> list;
UserDao database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        database=UserDatabase.getInstance(this).getDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
              list=  database.getAllData();

              new Handler(Looper.getMainLooper()).post(new Runnable() {
                  @Override
                  public void run() {
                      userListAdapter =new UserListAdapter(UserList.this,list);
                      recyclerView.setAdapter(userListAdapter);

                  }
              });

            }
        }).start();




    }


}