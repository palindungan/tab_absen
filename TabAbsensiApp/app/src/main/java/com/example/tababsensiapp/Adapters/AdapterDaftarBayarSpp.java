package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.bayar_spp;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdapterDaftarBayarSpp extends RecyclerView.Adapter<AdapterDaftarBayarSpp.DaftarBayarSppViewHolder> {

    Context context;
    ArrayList<bayar_spp> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;

    public AdapterDaftarBayarSpp(Context context, ArrayList<bayar_spp> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public AdapterDaftarBayarSpp.DaftarBayarSppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_bayar_spp, parent, false);
        return new DaftarBayarSppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarBayarSpp.DaftarBayarSppViewHolder holder, int position) {
        holder.tvWaktu.setText("Waktu : " + dataModelArrayList.get(position).getWaktu());
        holder.tvTotalPertemuan.setText("Total Pertemuan : " + dataModelArrayList.get(position).getTotal_pertemuan());
        holder.tvTotalSpp.setText("Total Spp : " + dataModelArrayList.get(position).getTotal_spp());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarBayarSppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvWaktu, tvTotalPertemuan, tvTotalSpp;

        public DaftarBayarSppViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvTotalPertemuan = itemView.findViewById(R.id.tv_total_pertemuan);
            tvTotalSpp = itemView.findViewById(R.id.tv_total_spp);

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
        AdapterDaftarBayarSpp.clickListener = clickListener;
    }
}
