package com.tackedev.coffee_shop.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tackedev.coffee_shop.R;
import com.tackedev.coffee_shop.activity.product.ProductListActivity;
import com.tackedev.coffee_shop.dao.UserDao;
import com.tackedev.coffee_shop.model.User;

public class LoginActivity extends AppCompatActivity {
    private UserDao userDao;

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getLoginUser().isEmpty()) {
            userDao = new UserDao(this);

            txtEmail = findViewById(R.id.txtEmail);
            txtPassword = findViewById(R.id.txtPassword);
            btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(this::onBtnLoginClick);
        } else {
            startProductActivity();
        }
    }

    private void startProductActivity() {
        Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
        startActivity(intent);
    }

    private void onBtnLoginClick(View view) {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        User loginUser = new User(email, password);
        if (userDao.isExist(loginUser)) {
            updateLoginUser(email);
            Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
            startProductActivity();
        } else {
            updateLoginUser(null);
            Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_LONG).show();
        }
    }

    private void updateLoginUser(String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("app.user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (email != null) {
            editor.putString("email", email);
        } else {
            editor.clear();
        }
        editor.apply();
    }

    private String getLoginUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("app.user", MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }


}