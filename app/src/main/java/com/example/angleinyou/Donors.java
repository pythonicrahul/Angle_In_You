package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Donors extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);
        value = getIntent().getStringExtra("checka");
        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_main, true);
        if(value.equals("organ")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllDonors() ).commit();
        }else if(value.equals("blood")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BloodDonors() ).commit();
        } else if (value.equals("plasma")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PlasmaDonors() ).commit();
        }

        bottomMenu();

    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.bottom_nav_main:
                        if(value.equals("organ")){
                            fragment = new AllDonors();
                        }else if(value.equals("blood")){
                            fragment = new BloodDonors();
                        } else if (value.equals("plasma")) {
                            fragment = new PlasmaDonors();
                        }

                        break;
                    case R.id.bottom_nav_user:
                        fragment = new User();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
    }
}