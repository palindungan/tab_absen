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
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterPengajarDaftarKelasAktif extends RecyclerView.Adapter<AdapterPengajarDaftarKelasAktif.AdapterPengajarDaftarKelasAktifViewHolder> {

    Context context;
    ArrayList<Pertemuan> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;

    public AdapterPengajarDaftarKelasAktif(Context context, ArrayList<Pertemuan> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterPengajarDaftarKelasAktif.AdapterPengajarDaftarKelasAktifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_pengajar_daftar_kelas_aktif, parent, false);
        return new AdapterPengajarDaftarKelasAktifViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPengajarDaftarKelasAktif.AdapterPengajarDaftarKelasAktifViewHolder holder, int position) {
        // holder.tvNamaPengajar.setText("Nama Pengajar : " + dataModelArrayList.get(position).getNama_pengajar());

        String nama_pelajaran = dataModelArrayList.get(position).getNama_mata_pelajaran();
        String hari_jadwal = dataModelArrayList.get(position).getHari_jadwal();
        String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
        String jam_berakhir = dataModelArrayList.get(position).getJam_berakhir();
        String harga_fee = dataModelArrayList.get(position).getHarga_fee();
        // holder.tvDetailKelasP.setText(nama_pelajaran + " (" + hari_jadwal + ", " + jam_mulai + " - " + jam_berakhir + ") / Rp " + harga_fee + "");
        holder.tvDetailKelasP.setText(nama_pelajaran + " (" + hari_jadwal + ", " + jam_mulai + " - " + jam_berakhir + ")");

        String hari_btn = dataModelArrayList.get(position).getHari_btn();
        String waktu_mulai = dataModelArrayList.get(position).getWaktu_mulai();
        holder.tvWaktuDetailMulai.setText("Dimulai : " + hari_btn + ", " + waktu_mulai);

        String waktu_berakhir = dataModelArrayList.get(position).getWaktu_berakhir();
        holder.tvWaktuDetailBerakhir.setText("Berakhir : " + hari_btn + ", " + waktu_berakhir);

        if (waktu_mulai.equals(waktu_berakhir)) {
            holder.tvWaktuDetailBerakhir.setVisibility(View.GONE);
        }

        String status_pertemuan = dataModelArrayList.get(position).getStatus_pertemuan();
        holder.tvStatusPertemuan.setText("Status Pertemuan : " + status_pertemuan);

        String status_konfirmasi = dataModelArrayList.get(position).getStatus_konfirmasi();
        holder.tvStatusKonfirmasi.setText("Status Konfirmasi : " + status_konfirmasi);

        String status_fee = dataModelArrayList.get(position).getStatus_fee();
        holder.tvStatusFee.setText("Status Fee : " + status_fee);

        String status_spp = dataModelArrayList.get(position).getStatus_spp();
        holder.tvStatusSPP.setText("Status SPP : " + status_spp);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterPengajarDaftarKelasAktifViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaPengajar, tvDetailKelasP, tvWaktuDetailMulai, tvWaktuDetailBerakhir, tvLokasiDetailMulai;
        protected TextView tvStatusPertemuan, tvStatusKonfirmasi, tvStatusFee,tvStatusSPP;

        public AdapterPengajarDaftarKelasAktifViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPengajar = itemView.findViewById(R.id.tv_nama_pengajar);
            tvDetailKelasP = itemView.findViewById(R.id.tv_detail_kelas_p);
            tvWaktuDetailMulai = itemView.findViewById(R.id.tv_waktu_detail_mulai);
            tvWaktuDetailBerakhir = itemView.findViewById(R.id.tv_waktu_detail_berakhir);

            tvStatusPertemuan = itemView.findViewById(R.id.tv_status_pertemuan);
            tvStatusKonfirmasi = itemView.findViewById(R.id.tv_status_konfirmasi);
//            tvLokasiDetailMulai = itemView.findViewById(R.id.tv_lokasi_detail_mulai);
            tvStatusFee = itemView.findViewById(R.id.tv_status_fee);
            tvStatusSPP= itemView.findViewById(R.id.tv_status_spp);

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
        AdapterPengajarDaftarKelasAktif.clickListener = clickListener;
    }
}
