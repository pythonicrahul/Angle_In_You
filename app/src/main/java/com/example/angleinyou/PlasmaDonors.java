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


public class PlasmaDonors extends Fragment {

    String organName;
    Button selBtn;
    String nextPageVal;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        selBtn = getView().findViewById(R.id.selBtn);
        MaterialSpinner organSpninner = getView().findViewById(R.id.organ3);
        organSpninner.setItems("Select Plasma", "Plasma A+", "Plasma A-", "Plasma B+", "Plasma B-", "Plasma AB+", "Plasma AB-", "Plasma O+", "Plasma O-");
        organSpninner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                organName = item;
                nextPageVal =item;
            }
        });

        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(organName.equals("Select Plasma")){
                    Toast.makeText(getContext(), "Select one plasma", Toast.LENGTH_SHORT).show();
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

        return inflater.inflate(R.layout.fragment_plasma_donors, container, false);
    }
}