package com.example.tababsenapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Model.pengajar.Pengajar;
import com.example.tababsenapp.R;
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

    public AdapterDaftarPengajar(Context context, ArrayList<Pengajar> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public DaftarPengajarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_pengajar, parent, false);
        return new DaftarPengajarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarPengajarViewHolder holder, int position) {
        holder.edtNama.setText(dataModelArrayList.get(position).getNama());
        holder.edtUsername.setText(dataModelArrayList.get(position).getUsername());
        holder.edtNoHP.setText(dataModelArrayList.get(position).getNo_hp());

        String alamat = sessionManager.getUploadUrl() + "image/pengajar/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamat).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPengajarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView edtNama, edtUsername, edtNoHP;
        protected ImageView ivFoto;

        public DaftarPengajarViewHolder(@NonNull View itemView) {
            super(itemView);

            edtNama = itemView.findViewById(R.id.edt_nama);
            edtUsername = itemView.findViewById(R.id.edt_username);
            edtNoHP = itemView.findViewById(R.id.edt_no_hp);
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
