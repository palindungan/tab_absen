package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pengajar;
import com.example.tababsensiapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarKelasPengajar extends RecyclerView.Adapter<AdapterDaftarKelasPengajar.DaftarPengajarKelasViewHolder> {

    Context context;
    ArrayList<Pengajar> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;

    public AdapterDaftarKelasPengajar(Context context, ArrayList<Pengajar> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public AdapterDaftarKelasPengajar.DaftarPengajarKelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_daftar_pengajar_kelas, parent, false);
        return new DaftarPengajarKelasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarKelasPengajar.DaftarPengajarKelasViewHolder holder, int position) {
        holder.tvNama.setText(dataModelArrayList.get(position).getNama());
        holder.tvUsername.setText("Username : " + dataModelArrayList.get(position).getUsername());
        holder.tvAlamat.setText("(" + dataModelArrayList.get(position).getAlamat() + ")");

        String alamat = baseUrl.getUrlUpload() + "image/pengajar/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamat).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPengajarKelasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvNama, tvUsername, tvAlamat;
        protected ImageView ivFoto;

        public DaftarPengajarKelasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            ivFoto = itemView.findViewById(R.id.iv_foto);

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
        AdapterDaftarKelasPengajar.clickListener = clickListener;
    }
}
