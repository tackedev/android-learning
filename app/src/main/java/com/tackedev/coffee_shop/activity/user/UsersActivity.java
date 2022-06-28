package com.tackedev.coffee_shop.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tackedev.coffee_shop.R;
import com.tackedev.coffee_shop.dao.UserDao;
import com.tackedev.coffee_shop.database.DatabaseOpener;
import com.tackedev.coffee_shop.model.User;

import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private UserDao userDao;

    private RecyclerView layoutUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        userDao = new UserDao(this);
        UserAdapter userAdapter = new UserAdapter(this, userDao);

        layoutUsers = findViewById(R.id.layoutUsers);
        layoutUsers.setAdapter(userAdapter);
        layoutUsers.setLayoutManager(new LinearLayoutManager(this));
    }
}