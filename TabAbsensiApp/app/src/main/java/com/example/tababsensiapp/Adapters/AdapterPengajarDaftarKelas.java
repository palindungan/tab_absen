package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.presenter.AdminKelasTampilKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.presenter.IAdminKelasTampilKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view.IAdminKelasTampilKelasView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterPengajarDaftarKelas extends RecyclerView.Adapter<AdapterPengajarDaftarKelas.AdapterPengajarDaftarKelasViewHolder> {

    Context context;
    ArrayList<Kelas> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;

    public AdapterPengajarDaftarKelas(Context context, ArrayList<Kelas> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterPengajarDaftarKelas.AdapterPengajarDaftarKelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_pengajar_daftar_kelas, parent, false);
        return new AdapterPengajarDaftarKelasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPengajarDaftarKelas.AdapterPengajarDaftarKelasViewHolder holder, int position) {
        String jumlah_murid = dataModelArrayList.get(position).getJumlah_murid();

        holder.tvNamaPelajaran.setText(dataModelArrayList.get(position).getNama_pelajaran() + " (" + jumlah_murid + " Murid)");
        holder.tvHari.setText("Jadwal Hari : " + dataModelArrayList.get(position).getHari());
        holder.tvJam.setText("Jadwal Jam : " + dataModelArrayList.get(position).getJam_mulai() + " - " + dataModelArrayList.get(position).getJam_berakhir());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterPengajarDaftarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaPelajaran, tvHari, tvJam;

        public AdapterPengajarDaftarKelasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelajaran = itemView.findViewById(R.id.tv_nama_pelajaran);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvJam = itemView.findViewById(R.id.tv_jam);

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
        AdapterPengajarDaftarKelas.clickListener = clickListener;
    }
}
