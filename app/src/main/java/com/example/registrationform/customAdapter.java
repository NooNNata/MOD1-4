package com.example.registrationform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.NyViewHokder> {

    private Context context;
    Activity activity;
    private ArrayList id, nama, no_telp, alamat, gender, olahraga, waktu;


    customAdapter(Activity activity, Context context, ArrayList id, ArrayList nama, ArrayList no_telp, ArrayList alamat, ArrayList gender, ArrayList olahraga, ArrayList waktu){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.nama = nama;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.gender = gender;
        this.olahraga = olahraga;
        this.waktu = waktu;
    }

    @NonNull
    @Override
    public NyViewHokder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new NyViewHokder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.NyViewHokder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.nama.setText(String.valueOf(nama.get(position)));
        holder.no_telp.setText(String.valueOf(no_telp.get(position)));
        holder.alamat.setText(String.valueOf(alamat.get(position)));
        holder.gender.setText(String.valueOf(gender.get(position)));
        holder.olahraga.setText(String.valueOf(olahraga.get(position)));
        holder.waktu.setText(String.valueOf(waktu.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, updateData.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nama", String.valueOf(nama.get(position)));
                intent.putExtra("no_telp", String.valueOf(no_telp.get(position)));
                intent.putExtra("alamat", String.valueOf(alamat.get(position)));
                intent.putExtra("gender", String.valueOf(gender.get(position)));
                intent.putExtra("olahraga", String.valueOf(olahraga.get(position)));
                intent.putExtra("waktu", String.valueOf(waktu.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class NyViewHokder extends RecyclerView.ViewHolder {

        TextView id, nama, no_telp, alamat, gender, olahraga, waktu;
        LinearLayout mainLayout;
        public NyViewHokder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamattampil);
            no_telp = itemView.findViewById(R.id.hp);
            gender = itemView.findViewById(R.id.gendertampil);
            olahraga = itemView.findViewById(R.id.instruktur);
            waktu = itemView.findViewById(R.id.waktu);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
