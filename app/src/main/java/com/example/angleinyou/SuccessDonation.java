package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SuccessDonation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_donation);
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ChoseRole.class));
//        Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
    }
}