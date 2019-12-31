package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Penggajian;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdapterDaftarPenggajian extends RecyclerView.Adapter<AdapterDaftarPenggajian.DaftarPenggajianViewHolder> {

    Context context;
    ArrayList<Penggajian> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;

    public AdapterDaftarPenggajian(Context context, ArrayList<Penggajian> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public AdapterDaftarPenggajian.DaftarPenggajianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_penggajian, parent, false);
        return new DaftarPenggajianViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarPenggajian.DaftarPenggajianViewHolder holder, int position) {
        holder.tvWaktu.setText("Waktu : " + dataModelArrayList.get(position).getWaktu());
        holder.tvTotalPertemuan.setText("Total Pertemuan : " + dataModelArrayList.get(position).getTotal_pertemuan());
        holder.tvTotalHargaFee.setText("Total Fee : " + dataModelArrayList.get(position).getTotal_harga_fee());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPenggajianViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvWaktu, tvTotalPertemuan, tvTotalHargaFee;

        public DaftarPenggajianViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvTotalPertemuan = itemView.findViewById(R.id.tv_total_pertemuan);
            tvTotalHargaFee = itemView.findViewById(R.id.tv_total_pertemuan);
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
        AdapterDaftarPenggajian.clickListener = clickListener;
    }
}
