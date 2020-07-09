package com.example.tababsenapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Model.murid.Murid;
import com.example.tababsenapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarMurid extends RecyclerView.Adapter<AdapterDaftarMurid.DaftarMuridViewHolder> {
    Context context;
    ArrayList<Murid> dataModelArrayList;

    SessionManager sessionManager;
    private static ClickListener clickListener;

    public AdapterDaftarMurid(Context context, ArrayList<Murid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public DaftarMuridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_murid,parent,false);
        return new DaftarMuridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarMuridViewHolder holder, int position) {

        holder.txtNama.setText(dataModelArrayList.get(position).getNama());
        holder.txtNamaWaliMurid.setText(dataModelArrayList.get(position).getNama_wali_murid());
        holder.txtAlamat.setText(dataModelArrayList.get(position).getAlamat());

        String alamat = sessionManager.getUploadUrl() + "image/murid/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamat).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView txtNama ,txtNamaWaliMurid, txtAlamat;
        protected ImageView ivFoto;

        public DaftarMuridViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            txtNamaWaliMurid = itemView.findViewById(R.id.txt_nama_wali_murid);
            txtAlamat = itemView.findViewById(R.id.txt_alamat);

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

    public  void setOnItemClickListener(ClickListener clickListener){
        AdapterDaftarMurid.clickListener = clickListener;
    }

}
