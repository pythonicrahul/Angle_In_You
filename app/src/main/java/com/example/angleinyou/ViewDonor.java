package com.example.angleinyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ViewDonor extends AppCompatActivity {
    private String donation_id,link;
    private ImageView imageView;
    private TextView nameTextView, addressTextView, emailTextView, phoneTextView, organTextView, bloodTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donor);
        donation_id = getIntent().getStringExtra("donation_id");
        nameTextView = findViewById(R.id.SingleDonorName);
        imageView = findViewById(R.id.imageView7);
        bloodTextView = findViewById(R.id.textView17);
        addressTextView = findViewById(R.id.SingleDonorAddress);
        emailTextView = findViewById(R.id.SingleDonorEmail);
        phoneTextView = findViewById(R.id.SingleDonorPhone);
        organTextView = findViewById(R.id.SingleDonorOrganName);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("donate").child(donation_id);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String organName = snapshot.child("organName").getValue().toString();
                organTextView.setText(organName);
                if(organName.equals("Blood")){
                    imageView.setImageResource(R.drawable.blood);
                }else if(organName.equals("Kidney")){
                    imageView.setImageResource(R.drawable.kidney);
                }else if(organName.equals("Bones")){
                    imageView.setImageResource(R.drawable.toxic);
                }else if(organName.equals("Eyes")){
                    imageView.setImageResource(R.drawable.eye);
                }else if(organName.equals("Lungs")){
                    imageView.setImageResource(R.drawable.lungs);
                }else if(organName.equals("BoneMarrow")){
                    imageView.setImageResource(R.drawable.toxic);
                }else{
                    imageView.setImageResource(R.drawable.plasma);
                }
                link = snapshot.child("link").getValue(String.class);
                DatabaseReference user = FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(snapshot.child("userId").getValue(String.class)));
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nameTextView.setText(snapshot.child("name").getValue(String.class));
                        addressTextView.setText(snapshot.child("address").getValue(String.class));
                        emailTextView.setText(snapshot.child("email").getValue(String.class));
                        phoneTextView.setText(snapshot.child("phone").getValue(String.class));
                        bloodTextView.setText(snapshot.child("bloodGroup").getValue(String.class) + " "+ snapshot.child("age").getValue(String.class));
                        Toast.makeText(ViewDonor.this, "Link : "+ link, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void medicalShow(View view) {
        if(link.isEmpty()){
            Toast.makeText(this, "Donor has not given any certificate", Toast.LENGTH_SHORT).show();
        }else{
            Uri uri = Uri.parse(link); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}