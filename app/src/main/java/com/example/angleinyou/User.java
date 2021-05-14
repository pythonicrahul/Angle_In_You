package com.example.angleinyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


public class User extends Fragment {

    private String sno,donationCount,_EMAIL,_NAME,_PHONE;
    private String phoneNumber;
    private String name;
    private String email;
    private TextView Full_Name;
    private FirebaseDatabase root;
    private DatabaseReference reference,reference2,reference3;
    private Button updatePro;
    private Button LogOutPro;
    private MaterialCardView materialCardView;
    TextInputLayout namelayout,emaillayout,phonelayout;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        root = FirebaseDatabase.getInstance();
        namelayout = (TextInputLayout) getView().findViewById(R.id.name);
        emaillayout = (TextInputLayout) getView().findViewById(R.id.email);
        phonelayout = (TextInputLayout) getView().findViewById(R.id.phone);
        materialCardView = getView().findViewById(R.id.organ_layout);
        LogOutPro = getView().findViewById(R.id.LogOutBtn);
        updatePro = (Button) getView().findViewById(R.id.updateBtn);
        Full_Name = (TextView) getView().findViewById(R.id.full_name);


//        Toast.makeText(getContext(), ""+ SharedPrefManager.getInstance(getContext()).getUserId(), Toast.LENGTH_SHORT).show();
        reference2 = FirebaseDatabase.getInstance().getReference("users").child(SharedPrefManager.getInstance(getContext()).getUserId());
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 name = snapshot.child("name").getValue().toString();
                 email = snapshot.child("email").getValue().toString();
                try {
                    phoneNumber = snapshot.child("phone").getValue().toString();
                    phonelayout.getEditText().setText(phoneNumber);
                }catch (Exception e){
                    phoneNumber = "Please add a mobile Number";
                    phonelayout.getEditText().setHint(phoneNumber);
                }
                namelayout.getEditText().setText(name);
                emaillayout.getEditText().setText(email);
                Full_Name.setText(name);;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        updatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNameChanged() || isEmail_Changed() || isPhoneChanged()){
                    Toast.makeText(getContext(), "Data has been Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Data is same can not be updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LogOutPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPrefManager.getInstance(getContext()).logout()){
                    startActivity(new Intent(getContext(), MainActivity.class));
                    Toast.makeText(getContext(), "You are Logged Out Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference3 = FirebaseDatabase.getInstance().getReference().child("users").child(SharedPrefManager.getInstance(getContext()).getUserId());
                reference3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Intent intent = new Intent(getContext(), ChooseOrgan.class);
                        intent.putExtra("bloodGroup", snapshot.child("bloodGroup").getValue(String.class));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private boolean isPhoneChanged() {
        if(!phoneNumber.equals(phonelayout.getEditText().getText().toString())){
            String phoneNumber = phonelayout.getEditText().getText().toString();
            reference.child(SharedPrefManager.getInstance(getContext()).getUserId()).child("phone").setValue(phoneNumber);
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!name.equals(namelayout.getEditText().getText().toString())){
            String name = namelayout.getEditText().getText().toString();
            reference.child(SharedPrefManager.getInstance(getContext()).getUserId()).child("name").setValue(name);
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmail_Changed() {
        if(!email.equals(emaillayout.getEditText().getText().toString())){
            String email = emaillayout.getEditText().getText().toString();
            reference.child(SharedPrefManager.getInstance(getContext()).getUserId()).child("email").setValue(email);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        sno = SharedPrefManager.getInstance(getContext()).getUserId();
        reference = FirebaseDatabase.getInstance().getReference("users");
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}