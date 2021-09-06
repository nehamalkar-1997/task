package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class DataBinding extends AppCompatActivity {
  RecyclerView recyclerView;
  UserRecyclerAdapter userRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        UserRecyclerAdapter userRecyclerAdapter = new UserRecyclerAdapter(getUserList());
        recyclerView.setAdapter(userRecyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        /*// setContentView(R.layout.activity_data_binding);

        ActivityDataBindingBinding activityDataBindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);
        //   activityDataBindingBinding.setMyVariable("Hello DataBinding");
        User user = new User("Neha",24,true,"https://i.picsum.photos/id/237/200/200.jps");
        activityDataBindingBinding.setUser(user);

        activityDataBindingBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DataBinding.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("Jhon Doe", 70, true));
        userList.add(new User("Charles Dickens", 70, true));
        userList.add(new User("Harry Potter", 70, false));
        userList.add(new User("Jessica Simpson", 70, true));
        userList.add(new User("Paul Addams", 70, false));
        return userList;
    }
}