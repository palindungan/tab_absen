package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.AdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.IAdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterPengajarDaftarKelasMurid extends RecyclerView.Adapter<AdapterPengajarDaftarKelasMurid.AdapterPengajarDaftarKelasMuridViewHolder> {
    Context context;
    ArrayList<Murid> dataModelArrayList;

    BaseUrl baseUrl;
    private static ClickListener clickListener;

    public AdapterPengajarDaftarKelasMurid(Context context, ArrayList<Murid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterPengajarDaftarKelasMurid.AdapterPengajarDaftarKelasMuridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_kelas_murid, parent, false);
        return new AdapterPengajarDaftarKelasMuridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPengajarDaftarKelasMurid.AdapterPengajarDaftarKelasMuridViewHolder holder, int position) {
        holder.tvNama.setText(dataModelArrayList.get(position).getNama());
        holder.tvNamaWaliMurid.setText(dataModelArrayList.get(position).getNama_wali_murid());
        holder.tvAlamat.setText(dataModelArrayList.get(position).getAlamat());

        String alamat = baseUrl.getUrlUpload() + "image/murid/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamat).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);

        holder.btnHapus.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class AdapterPengajarDaftarKelasMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView ivFoto;
        protected TextView tvNama, tvNamaWaliMurid, tvAlamat;
        protected ImageButton btnHapus;

        public AdapterPengajarDaftarKelasMuridViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNamaWaliMurid = itemView.findViewById(R.id.tv_nama_wali_murid);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            btnHapus = itemView.findViewById(R.id.btn_hapus);

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
        AdapterPengajarDaftarKelasMurid.clickListener = clickListener;
    }
}
