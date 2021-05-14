package com.example.angleinyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Map;

public class DontateActivity extends AppCompatActivity {

    TextInputLayout addressLayout;
    private String addressVal,ageVal,stateVal,cityVal,genderVal,bloodGroupVal;
    String Id;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dontate);
        rootNode = FirebaseDatabase.getInstance();
        Id = SharedPrefManager.getInstance(getApplicationContext()).getUserId();
        genderVal = "Male";
        ageVal = "18";
        stateVal = "Andhra Pradesh";
        bloodGroupVal = "A+";
        cityVal = "Adilabad";
        reference = rootNode.getReference("users").child(Id);
        // Hooks
        addressLayout = findViewById(R.id.address);

        MaterialSpinner stateSpinner = (MaterialSpinner) findViewById(R.id.state);
        stateSpinner.setItems("Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry");
        stateSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                stateVal = item;
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner citySpinner = findViewById(R.id.city);
        citySpinner.setItems("Adilabad",
                "Anantapur",
                "Chittoor",
                "Kakinada",
                "Guntur",
                "Hyderabad",
                "Karimnagar",
                "Khammam",
                "Krishna",
                "Kurnool",
                "Mahbubnagar",
                "Medak",
                "Nalgonda",
                "Nizamabad",
                "Ongole",
                "Hyderabad",
                "Srikakulam",
                "Nellore",
                "Visakhapatnam",
                "Vizianagaram",
                "Warangal",
                "Eluru",
                "Kadapa");
        citySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                cityVal = item;
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner genderSpninner = findViewById(R.id.gender);
        genderSpninner.setItems("Male", "Female", "Transgender");
        genderSpninner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                genderVal = item;
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner groupSpinner = findViewById(R.id.blood_group);
        groupSpinner.setItems( "A+", "A-", "B-", "B+", "AB+", "AB-", "O+", "O-");
        groupSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                bloodGroupVal = (String) item;

            }
        });

        MaterialSpinner ageSpinner = findViewById(R.id.age);
        ageSpinner.setItems("18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50");
        ageSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                ageVal = item;
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });


    }

    public void updateData(View view) {
        addressVal = addressLayout.getEditText().getText().toString();
        if(addressVal.isEmpty()){
            Toast.makeText(this, "Please enter a valid address", Toast.LENGTH_SHORT).show();
            return;
        }
//        Query checkUser = reference.orderByChild("UserId");
//        UserHelperClass userHelperClass = new UserHelperClass();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);
//                userHelperClass.setCity(cityVal);
//                userHelperClass.setAge(ageVal);
//                userHelperClass.setGender(genderVal);
//                userHelperClass.setState(stateVal);
//                userHelperClass.setAddress(addressVal);
//                reference.updateChildren((Map<String, Object>) userHelperClass);
////                System.out.println(userHelperClass);
////                String KL = userHelperClass.getAge() + userHelperClass.getCity() +userHelperClass.getEmail() +userHelperClass.getPhone();
////                Toast.makeText(DontateActivity.this, "DEca" + KL, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("city", cityVal);
        userMap.put("state", stateVal);
        userMap.put("gender", genderVal);
        userMap.put("age", ageVal);
        userMap.put("address", addressVal);
        userMap.put("bloodGroup", bloodGroupVal);
        reference.updateChildren(userMap);
        Intent intent = new Intent(getApplicationContext(), ChooseOrgan.class);
        intent.putExtra("city", cityVal);
        intent.putExtra("state", stateVal);
        intent.putExtra("address", addressVal);
        intent.putExtra("bloodGroup", bloodGroupVal);
        Toast.makeText(this, "City: " + cityVal, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();

//        reference.child(Id).updateChildren()
//        Toast.makeText(this, "Value 1 "+ addressVal+"Value 2 "+ ageVal+ "Value 3 "+genderVal+"Value 4 "+stateVal+"Value 5 "+cityVal, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Address: "+ textView.getText(), Toast.LENGTH_SHORT).show();

    }


}