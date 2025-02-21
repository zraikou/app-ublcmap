package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnLogin;
    EditText etUsername, etPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Set up the Enter key action for the username EditText
        etUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    etPwd.requestFocus(); // Move focus to the password field
                    return true; // Indicate that the action has been handled
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLoggedId = dbHelper.checkUser(etUsername.getText().toString(), etPwd.getText().toString());
                if(isLoggedId){
                    Intent intent = new Intent(Login.this, SplashScreen.class);
                    startActivity(intent);
                } else
                    Toast.makeText(Login.this, "Login Failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Define the launchSettings method
    public void launchSettings(View view) {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}