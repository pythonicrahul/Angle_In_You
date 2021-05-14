package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UpateUser extends AppCompatActivity {
    private String phoneNumber, nameVal, emailVal, passwordVal;
    TextInputLayout name, email, password;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upate_user);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Toast.makeText(this, "Phone " + phoneNumber, Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        nameVal = name.getEditText().getText().toString().trim();
        emailVal = email.getEditText().getText().toString().trim();
        passwordVal = email.getEditText().getText().toString().trim();


        if (!nameVal.isEmpty() && !emailVal.isEmpty() && !passwordVal.isEmpty()) {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");
            String id = reference.push().getKey();
            UserHelperClass userHelperClass = new UserHelperClass(nameVal, emailVal, phoneNumber, "10", "Male", passwordVal, id);
            reference.child(id).setValue(userHelperClass);
            SharedPrefManager.getInstance(getApplicationContext()).userLogin(id, userHelperClass.getName());
            startActivity(new Intent(getApplicationContext(), ChoseRole.class));
        } else{
            Toast.makeText(this, "Please recheck all values.", Toast.LENGTH_SHORT).show();
        }
    }
}