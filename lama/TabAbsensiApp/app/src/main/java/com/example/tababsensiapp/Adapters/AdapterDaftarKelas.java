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
        String jumlah_murid = dataModelArrayList.get(position).getJumlah_murid();

        holder.tvNamaPelajaran.setText(dataModelArrayList.get(position).getNama_pelajaran() + " (" + jumlah_murid + " Murid)");
        holder.tvHari.setText("Hari : " + dataModelArrayList.get(position).getHari());
        holder.tvJam.setText("Jam : " + dataModelArrayList.get(position).getJam_mulai() + " - " + dataModelArrayList.get(position).getJam_berakhir());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.adminKelasTampilKelasPresenter.hapusAkun(dataModelArrayList.get(position).getId_kelas_p());
            }
        });

        String nama_sharing = dataModelArrayList.get(position).getNama_sharing();

        if (!nama_sharing.equals("kosong")){
            holder.btnHapus.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterDaftarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaPelajaran, tvHari, tvJam;
        protected ImageButton btnHapus;

        IAdminKelasTampilKelasPresenter adminKelasTampilKelasPresenter;

        public AdapterDaftarKelasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelajaran = itemView.findViewById(R.id.tv_nama_pelajaran);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvJam = itemView.findViewById(R.id.tv_jam);
            btnHapus = itemView.findViewById(R.id.btn_hapus);

            adminKelasTampilKelasPresenter = new AdminKelasTampilKelasPresenter(context, (IAdminKelasTampilKelasView) context);

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
