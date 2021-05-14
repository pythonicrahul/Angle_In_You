package com.example.angleinyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class ChooseOrgan extends AppCompatActivity {
    private String organVal,state,city,address,bloodGroup,link;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference donationreference;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_organ);
        donationreference = FirebaseDatabase.getInstance().getReference("donate");
        bloodGroup = getIntent().getStringExtra("bloodGroup");
        editText = findViewById(R.id.certificateLink);
        organVal = "Blood";

//        Toast.makeText(this, "bloodGroup:" + bloodGroup, Toast.LENGTH_SHORT).show();
        MaterialSpinner organSpninner = findViewById(R.id.organ_layout);
        organSpninner.setItems("Blood", "Lungs", "Kidney", "Liver","Pancreas","Heart","Intestine", "Plasma A+", "Plasma A-", "Plasma B+", "Plasma B-", "Plasma AB+", "Plasma AB-", "Plasma O+", "Plasma O-");
        organSpninner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                organVal = item;
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void donateBtn(View view) {
        String id = donationreference.push().getKey();
        DonationHelper donationHelper;
        link = editText.getText().toString();
        if(link.isEmpty()){
            Toast.makeText(this, "Please enter url of your certificate...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(organVal.equals("Blood")){
            donationHelper = new DonationHelper(organVal,id,SharedPrefManager.getInstance(this).getUserId(), bloodGroup, link);
        }else{
            donationHelper = new DonationHelper(organVal,id,SharedPrefManager.getInstance(this).getUserId(), link);
        }

        donationreference.child(id).setValue(donationHelper);
        startActivity(new Intent(getApplicationContext(),SuccessDonation.class));
    }

}