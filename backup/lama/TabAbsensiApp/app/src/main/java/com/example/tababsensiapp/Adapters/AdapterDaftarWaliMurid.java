package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.WaliMurid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarWaliMurid extends RecyclerView.Adapter<AdapterDaftarWaliMurid.DaftarWaliMuridViewHolder> {

    Context context;
    ArrayList<WaliMurid> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;

    public AdapterDaftarWaliMurid(Context context, ArrayList<WaliMurid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public DaftarWaliMuridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_wali_murid, parent, false);
        return new DaftarWaliMuridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarWaliMuridViewHolder holder, int position) {
        holder.txtNama.setText("Nama Wali Murid : " + dataModelArrayList.get(position).getNama());
        holder.txtUsername.setText("Username : " + dataModelArrayList.get(position).getUsername());
        holder.txtNoHp.setText("No. Hp : " + dataModelArrayList.get(position).getNo_hp());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarWaliMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama, txtUsername, txtNoHp;

        public DaftarWaliMuridViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtNoHp = itemView.findViewById(R.id.txt_no_hp);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterDaftarWaliMurid.clickListener = clickListener;
    }
}
