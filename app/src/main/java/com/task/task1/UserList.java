package com.task.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {
    private UserListAdapter userListAdapter;
    List<UserEntity> list;
    UserDao database;
    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        searchBar= findViewById(R.id.search_bar);
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

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        List<UserEntity> filterList =new ArrayList<>();
        for (UserEntity items : list){
            if(items.getName1().toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);

            }
        }
        userListAdapter.filterList(filterList);
    }
}