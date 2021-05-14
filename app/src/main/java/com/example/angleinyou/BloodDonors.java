package com.example.angleinyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;


public class BloodDonors extends Fragment {

    String organName,bloodGroupVal;
    Button selBtn;
    String nextPageVal;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        selBtn = getView().findViewById(R.id.selBtn);
        MaterialSpinner groupSpinner = getView().findViewById(R.id.organ3);
        groupSpinner.setItems("Select Blood Group", "A+", "A-", "B-", "B+", "AB+", "AB-", "O+", "O-");
        groupSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                bloodGroupVal = (String) item;
                nextPageVal = (String) item;
            }
        });

        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bloodGroupVal.equals("Select Blood Group") ){
                    Toast.makeText(getContext(), "Select any blood group", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", nextPageVal);
                startActivity(intent);
//                Toast.makeText(getContext(), "Blood Group: "+bloodGroupVal+" "+" OrganName: "+organName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_donors, container, false);
    }
}