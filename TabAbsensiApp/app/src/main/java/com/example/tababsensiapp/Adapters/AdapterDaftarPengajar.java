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
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Pengajar;
import com.example.tababsensiapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarPengajar extends RecyclerView.Adapter<AdapterDaftarPengajar.DaftarPengajarViewHolder> {

    Context context;
    ArrayList<Pengajar> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public AdapterDaftarPengajar(Context context, ArrayList<Pengajar> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }

    @NonNull
    @Override
    public DaftarPengajarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_pengajar, parent, false);
        return new DaftarPengajarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarPengajarViewHolder holder, int position) {
        holder.txtNama.setText(dataModelArrayList.get(position).getNama());
        holder.txtUsername.setText(dataModelArrayList.get(position).getUsername());
        holder.txtNoHP.setText(dataModelArrayList.get(position).getNo_hp());


        String alamat = baseUrl.getUrlUpload() + "image/pengajar/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamat).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPengajarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama, txtUsername, txtNoHP;
        protected ImageView ivFoto;

        public DaftarPengajarViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtNoHP = itemView.findViewById(R.id.txt_no_hp);
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
        AdapterDaftarPengajar.clickListener = clickListener;
    }
}
