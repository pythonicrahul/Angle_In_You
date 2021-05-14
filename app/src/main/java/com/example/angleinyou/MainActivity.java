package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {
    private  boolean val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val = SharedPrefManager.getInstance(this).isLoggedIn();
        int time_out = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(val){
                    startActivity(new Intent(getApplicationContext(), ChoseRole.class) );
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class) );
                    finish();
                }
            }
        }, time_out);
    }

    public void nextPage(View view) {
        if(val){
            startActivity(new Intent(getApplicationContext(), ChoseRole.class));
        }
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}