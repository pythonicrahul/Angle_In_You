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
import android.widget.ImageView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;


 public class AllDonors extends Fragment {
    private ImageView lungs,kidney,heart,pancrease,liver,intestine;
//    private Boolean bloodChosen = false;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        lungs = getView().findViewById(R.id.lungsImg);
        kidney = getView().findViewById(R.id.kidneyImg);
        heart = getView().findViewById(R.id.heartImg);
        pancrease = getView().findViewById(R.id.pancreaseImg);
        liver = getView().findViewById(R.id.liverImg);
        intestine = getView().findViewById(R.id.intestineImg);

        lungs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Lungs");
                startActivity(intent);
            }
        });

        kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Kidney");
                startActivity(intent);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Heart");
                startActivity(intent);
            }
        });

        pancrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Pancreas");
                startActivity(intent);
            }
        });

        liver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Liver");
                startActivity(intent);
            }
        });

        intestine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowDonors.class);
                intent.putExtra("nextPageData", "Intestine");
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_donors, container, false);
    }
}