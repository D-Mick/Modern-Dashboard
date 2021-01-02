package com.rotimijohnson.moderndashboardscreen.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rotimijohnson.moderndashboardscreen.R;

public class profileActivity extends AppCompatActivity {

    private TextInputLayout fullname, email, number, password;
    private TextView profile_fullname, profile_shortname;

    String _FULLNAME, _NUMBER, _EMAIL, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");


        fullname = findViewById(R.id.name_profile);
        email = findViewById(R.id.email_profile);
        number = findViewById(R.id.number_profile);
        password = findViewById(R.id.password_profile);
        profile_fullname = findViewById(R.id.full_name);
        profile_shortname = findViewById(R.id.short_name);
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
         _FULLNAME = intent.getStringExtra("name");
         _NUMBER = intent.getStringExtra("phone");
         _EMAIL = intent.getStringExtra("email");
         _PASSWORD = intent.getStringExtra("password");


        fullname.getEditText().setText(_FULLNAME);
        email.getEditText().setText(_EMAIL);
        number.getEditText().setText(_NUMBER);
        profile_fullname.setText(_FULLNAME);
        profile_shortname.setText(_FULLNAME);
        password.getEditText().setText(_PASSWORD);
    }

    public void update(View view){
        if (isNameChanged() || isPasswordChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_FULLNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!_FULLNAME.equals(fullname.getEditText().getText().toString())){
            reference.child(_FULLNAME).child("name").setValue(fullname.getEditText().getText().toString());
            _FULLNAME = fullname .getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }


}
