package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

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
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_kelas, parent, false);
        return new AdapterDaftarKelasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarKelas.AdapterDaftarKelasViewHolder holder, int position) {
        holder.tvNamaPelajaran.setText(dataModelArrayList.get(position).getNama_pelajaran());
        holder.tvHargaFee.setText(dataModelArrayList.get(position).getHarga_fee());
        holder.tvHari.setText(dataModelArrayList.get(position).getHari());
        holder.tvJamMulai.setText(dataModelArrayList.get(position).getJam_mulai());
        holder.tvJamBerakhir.setText(dataModelArrayList.get(position).getJam_berakhir());

        holder.btnDataKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.btnDataMurid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterDaftarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaPelajaran, tvHargaFee, tvHari, tvJamMulai, tvJamBerakhir;
        protected Button btnDataKelas, btnDataMurid;

        public AdapterDaftarKelasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelajaran = itemView.findViewById(R.id.tv_nama_pelajaran);

            tvHargaFee = itemView.findViewById(R.id.tv_harga_fee);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvJamMulai = itemView.findViewById(R.id.tv_jam_mulai);
            tvJamBerakhir = itemView.findViewById(R.id.tv_jam_berakhir);
            btnDataKelas = itemView.findViewById(R.id.btn_data_kelas);
            btnDataMurid = itemView.findViewById(R.id.btn_data_murid);

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
