package com.example.tree_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tree_map.SQL.DatabaseHelper;
import com.example.tree_map.helpers.InputValidation;

public class LoginActivity extends AppCompatActivity {

    private final AppCompatActivity activity = LoginActivity.this;
    private EditText eEmail;
    private EditText ePassword;
    private Button eLogin ;
    private Button eRegister ;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initobjects();

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //              code to go to new activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initobjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
    }

    private void initViews() {
        eEmail = findViewById(R.id.et_email);
        ePassword = findViewById(R.id.et_password);
        eLogin = findViewById(R.id.bt_login);
        eRegister = findViewById(R.id.bt_register);
    }

    private void loginUser(){
        if (!inputValidation.isEditTextFilled(eEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextEmail(eEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(ePassword, getString(R.string.error_message_email))) {
            return;
        }
        if (databaseHelper.checkUser(eEmail.getText().toString().trim()
                , ePassword.getText().toString().trim())) {
            Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(accountsIntent);
        } else {
            // Snack Bar to show success message that record is wrong
//            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            Toast.makeText(LoginActivity.this,"Incorrect credentials entered",Toast.LENGTH_SHORT).show();
        }
    }
}