package com.example.tree_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tree_map.SQL.DatabaseHelper;
import com.example.tree_map.helpers.InputValidation;
import com.example.tree_map.model.User;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText eName;
    private EditText eEmail;
    private EditText ePassword;
    private EditText eRePassword;
    private Button eRegister;
    private TextView eloginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initObjects();

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        eloginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        eName = findViewById(R.id.et_name);
        eEmail = findViewById(R.id.et_reg_email);
        ePassword = findViewById(R.id.et_regpass);
        eRePassword = findViewById(R.id.et_regrepass);
        eRegister = findViewById(R.id.bt_ogregister);
        eloginLink = findViewById(R.id.tv_loginLink);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    private void registerUser() {
        if (!inputValidation.isEditTextFilled(eName,getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(eEmail,getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextEmail(eEmail,getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(ePassword,getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isEditTextMatches(ePassword, eRePassword,getString(R.string.error_password_match))) {
            return;
        }
        if (!databaseHelper.checkUser(eEmail.getText().toString().trim())) {
            user.setName(eName.getText().toString().trim());
            user.setEmail(eEmail.getText().toString().trim());
            user.setPassword(ePassword.getText().toString().trim());
            databaseHelper.addUser(user);
//          code to go to new activity
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            // Snack Bar to show success message that record saved successfully
//            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
//            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
        }
    }


}