package com.rotimijohnson.moderndashboardscreen.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rotimijohnson.moderndashboardscreen.MainActivity;
import com.rotimijohnson.moderndashboardscreen.R;
import com.rotimijohnson.moderndashboardscreen.fragment.profileActivity;

public class loginActivity extends AppCompatActivity {
    private TextInputLayout name, password;
    private Button login,dont_have;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name_login);
        password = findViewById(R.id.password_login);
        login = findViewById(R.id.login);
        dont_have = findViewById(R.id.dont_have_accnt);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValidateName() | !validatePassword()){
                    return;
                }else {
                    isUser();
                }
            }
        });

        dont_have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void isUser() {
        final String userEnteredName = name.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("name").equalTo(userEnteredName);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    name.setError(null);
                    name.setErrorEnabled(false);

                    String passwordFromDatabase = dataSnapshot.child(userEnteredName).child("password").getValue(String.class);

                    if (passwordFromDatabase.equals(userEnteredPassword)){
                        name.setError(null);
                        name.setErrorEnabled(false);

                        String nameFromDatabase = dataSnapshot.child(userEnteredName).child("name").getValue(String.class);
                        String phoneNoFromDatabase = dataSnapshot.child(userEnteredName).child("phoneNo").getValue(String.class);
                        String emailFromDatabase = dataSnapshot.child(userEnteredName).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("name",nameFromDatabase);
                        intent.putExtra("password", passwordFromDatabase);
                        intent.putExtra("phoneNo",phoneNoFromDatabase);
                        intent.putExtra("email",emailFromDatabase);

//                        progressDialog.show();
                        startActivity(intent);
                    }else{
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }else {
                    name.setError("No such User exist");
                    name.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private Boolean ValidateName() {
        String val = name.getEditText().getText().toString();

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else {
            name.setErrorEnabled(false);
            name.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}
