package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Models.Murid;
import com.its.bigstars.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataMuridList extends RecyclerView.Adapter<AdapterDataMuridList.DataMuridListViewHolder> {

    Context context;
    ArrayList<Murid> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataMuridList(Context context, ArrayList<Murid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDataMuridList.DataMuridListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_murid_list, parent, false);
        return new DataMuridListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMuridList.DataMuridListViewHolder holder, int position) {
        holder.txtNama.setText("Nama : " + dataModelArrayList.get(position).getNama());
        holder.txtNamaWaliMurid.setText("Wali Murid : " + dataModelArrayList.get(position).getNama_wali_murid());
        holder.txtAlamat.setText("Alamat : " + dataModelArrayList.get(position).getAlamat());

        String alamatFoto = baseUrl.getUrlUpload() + "image/murid/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamatFoto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DataMuridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama, txtNamaWaliMurid, txtAlamat;
        protected ImageView ivFoto;

        public DataMuridListViewHolder(@NonNull View itemView) {
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

    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterDataMuridList.clickListener = clickListener;
    }
}
