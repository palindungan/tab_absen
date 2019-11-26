package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pengajar;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarPengajarKelas extends RecyclerView.Adapter<AdapterDaftarPengajarKelas.DaftarPengajarKelasViewHolder> {

    Context context;
    ArrayList<Pengajar> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;

    public AdapterDaftarPengajarKelas(Context context, ArrayList<Pengajar> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterDaftarPengajarKelas.DaftarPengajarKelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarPengajarKelas.DaftarPengajarKelasViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPengajarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public DaftarPengajarKelasViewHolder(@NonNull View itemView) {
            super(itemView);

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
        AdapterDaftarPengajarKelas.clickListener = clickListener;
    }
}
