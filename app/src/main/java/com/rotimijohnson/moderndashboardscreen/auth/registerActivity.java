package com.rotimijohnson.moderndashboardscreen.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rotimijohnson.moderndashboardscreen.MainActivity;
import com.rotimijohnson.moderndashboardscreen.R;
import com.rotimijohnson.moderndashboardscreen.util.UserHelper;

public class registerActivity extends AppCompatActivity {
    private TextInputLayout name,email, password, phoneNo;
    private Button register, have_account;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        have_account = findViewById(R.id.i_have_accnt);
        phoneNo = findViewById(R.id.phone_register);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String regName = name.getEditText().getText().toString();
                String regEmail = email.getEditText().getText().toString();
                String regPassword = password.getEditText().getText().toString();
                String regPhoneNo = phoneNo.getEditText().getText().toString();

                UserHelper userHelper = new UserHelper(regName,regEmail, regPassword, regPhoneNo);

                if (!ValidateEmail() | !validatePassword() | ValidateName()) {
                    return;
                }

                Intent intent = new Intent(registerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                reference.child(regName).setValue(userHelper);
            }
        });

        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }


    private Boolean ValidateName() {
        String val = name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\W{4,20}\\Z";

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 20) {
            name.setError("UserName too long");
            return false;
        }else if (!val.matches(noWhiteSpace)){
            name.setError("White Spaces are not allowed");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private Boolean ValidateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String passwordval = "^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordval)) {
            password.setError("Password must contain at least 8 letters, one lowercase, one uppercase, one number, one special character");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}
