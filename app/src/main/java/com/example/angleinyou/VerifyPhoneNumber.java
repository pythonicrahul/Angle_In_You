package com.example.angleinyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumber extends AppCompatActivity {
    private String phoneNumber;
    private EditText otpEdit;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;
    private String verificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);
        fAuth = FirebaseAuth.getInstance();
        otpEdit = findViewById(R.id.otpEditText);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
//        Toast.makeText(this, "JK"+phoneNumber, Toast.LENGTH_SHORT).show();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                progressBar.setVisibility(View.GONE);
                verificationId = s;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        };

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(fAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void verifyOtp(View view) {

        String userOtp = otpEdit.getText().toString();
        if(userOtp.length() == 6){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, userOtp);
            veriftyAuth(credential);
        }else{
            otpEdit.setError("Valid Otp is required");
        }

    }

    private void veriftyAuth(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            private DatabaseReference reference;

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    reference = FirebaseDatabase.getInstance().getReference();
                    Query query = reference.child("users").orderByChild("phone").equalTo(phoneNumber);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot issue: snapshot.getChildren()){
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(issue.child("userId").getValue(String.class), issue.child("name").getValue(String.class));
//                                    SharedPrefManager.getInstance(getApplicationContext()).DecideRole(true);
                                    startActivity(new Intent(getApplicationContext(), ChoseRole.class));
                                }
//                                DataSnapshot dataSnapshot = snapshot.getChildren();
//                                Toast.makeText(VerifyPhoneNumber.this, "Snapshot "+ snapshot, Toast.LENGTH_SHORT).show();
//                                Toast.makeText(VerifyPhoneNumber.this, "Name: "+snapshot.child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(VerifyPhoneNumber.this, "Val : "+ snapshot.child("userId").getValue(String.class), Toast.LENGTH_SHORT).show();
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(snapshot.child("userId").getValue(String.class), snapshot.child("name").getValue(String.class));
//                                SharedPrefManager.getInstance(getApplicationContext()).DecideRole(true);
//                                startActivity(new Intent(getApplicationContext(), Donors.class));
////                                startActivity(new Intent());
//                                finish();
                            }else{
//                                Toast.makeText(getApplicationContext(), "Authentication is very successfully Done !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UpateUser.class);
                                intent.putExtra("phoneNumber",phoneNumber);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}