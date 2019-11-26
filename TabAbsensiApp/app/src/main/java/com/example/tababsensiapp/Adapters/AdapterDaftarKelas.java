package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Kelas;

import java.util.ArrayList;

import butterknife.ButterKnife;

//RecyclerView.Adapter<AdapterDaftarPengajarKelas.DaftarPengajarKelasViewHolder>
public class AdapterDaftarKelas extends RecyclerView.Adapter<AdapterDaftarKelas.AdapterDaftarKelasViewHolder> {

    Context context;
    ArrayList<Kelas> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;

    public AdapterDaftarKelas(Context context, ArrayList<Kelas> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterDaftarKelas.AdapterDaftarKelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarKelas.AdapterDaftarKelasViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterDaftarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        public AdapterDaftarKelasViewHolder(@NonNull View itemView) {
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
        AdapterDaftarKelas.clickListener = clickListener;
    }
}
