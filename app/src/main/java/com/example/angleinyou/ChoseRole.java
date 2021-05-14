package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ChoseRole extends AppCompatActivity {

//    private LinearLayout plasma,blood,oragn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_role);

//        if(SharedPrefManager.getInstance(this).isRoleDecided()){
//            startActivity(new Intent(getApplicationContext(), Donors.class));
//            finish();
//        }
//        plasma = findViewById(R.id.plasmaLayout);
//        blood = findViewById(R.id.bloodLayout);
//        oragn = findViewById(R.id.organLayout);


    }



    public void DonationClick(View view) {
        SharedPrefManager.getInstance(this).DecideRole(true);
        startActivity(new Intent(getApplicationContext(), DontateActivity.class));
    }

    public void bloodDonate(View view) {

        Intent intent = new Intent(getApplicationContext(), Donors.class);
        intent.putExtra("checka","blood");
        startActivity(intent);

    }

    public void organDonate(View view) {
        Intent intent = new Intent(getApplicationContext(), Donors.class);
        intent.putExtra("checka","organ");
        startActivity(intent);
    }

    public void plasmaDonate(View view) {
        Intent intent = new Intent(getApplicationContext(), Donors.class);
        intent.putExtra("checka","plasma");
        startActivity(intent);
    }
//
//    public void SeekerClick(View view) {
//        SharedPrefManager.getInstance(this).DecideRole(true);
//        startActivity(new Intent(getApplicationContext(), Donors.class));
//    }
}