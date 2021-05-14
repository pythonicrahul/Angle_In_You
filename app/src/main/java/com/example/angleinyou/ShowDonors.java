package com.example.angleinyou;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDonors extends AppCompatActivity {
    TextView textView;
    DatabaseReference reference ;
    RecyclerView recyclerView;
    List<DonationHelper> donorsList;
    AdapterDonor adapterDonor;
    String Value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_donors);
        textView = findViewById(R.id.textView12);
        recyclerView = findViewById(R.id.recyclerView);
        Query query;
        donorsList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        Value = getIntent().getStringExtra("nextPageData");
        if(Value.equals("A+") || Value.equals("A-") || Value.equals("B-") || Value.equals("B+") || Value.equals("O+") || Value.equals("O-") || Value.equals("AB-") || Value.equals("AB+")){
            query = reference.child("donate").orderByChild("bloodGroup").equalTo(getIntent().getStringExtra("nextPageData"));
        }else{
            query = reference.child("donate").orderByChild("organName").equalTo(getIntent().getStringExtra("nextPageData"));
        }
//        Query query = reference.child("donate").orderByChild("organName").equalTo(getIntent().getStringExtra("nextPageData"));

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
//                        Toast.makeText(ShowDonors.this, " DEka: "+ issue.child("userId").child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
                        donorsList.add(issue.getValue(DonationHelper.class));
//                        Toast.makeText(ShowDonors.this, "Organ : " + donationHelper.getOrganName(), Toast.LENGTH_SHORT).show();
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapterDonor = new AdapterDonor(getApplicationContext(), donorsList);
                    recyclerView.setAdapter(adapterDonor);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        textView.setText( getIntent().getStringExtra("nextPageData") );

    }
}