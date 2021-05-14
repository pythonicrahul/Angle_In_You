package com.example.angleinyou;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class AdapterDonor extends RecyclerView.Adapter<AdapterDonor.ViewHolder> {
    LayoutInflater layoutInflater;
    List<DonationHelper> donors;
    Context ctx;

    public AdapterDonor(Context ctx, List<DonationHelper> donors){
        this.layoutInflater = LayoutInflater.from(ctx);
        this.donors =  donors;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_donor, parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.donar.setText(donors.get(position).getOrganName());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(donors.get(position).getUserId());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.location.setText(snapshot.child("city").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, ViewDonor.class);
                intent.putExtra("donation_id", donors.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return donors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView donar,location;
        Button btn;
        public ViewHolder(View itemView){
            super(itemView);
            donar = itemView.findViewById(R.id.donorName);
            location = itemView.findViewById(R.id.location);
            btn = itemView.findViewById(R.id.know_more_btn);
        }
    }
}
