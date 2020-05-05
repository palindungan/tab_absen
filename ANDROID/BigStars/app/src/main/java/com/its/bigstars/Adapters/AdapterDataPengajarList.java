package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Activities.Data.Pengajar.List.DataPengajarListActivity;
import com.its.bigstars.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Models.Pengajar;
import com.its.bigstars.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataPengajarList extends RecyclerView.Adapter<AdapterDataPengajarList.DataPengajarListViewHolder> {
    Context context;
    ArrayList<Pengajar> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataPengajarList(Context context, ArrayList<Pengajar> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDataPengajarList.DataPengajarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_pengajar_list, parent, false);
        return new DataPengajarListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPengajarList.DataPengajarListViewHolder holder, int position) {
        String kode = dataModelArrayList.get(position).getId_pengajar();
        String nama = dataModelArrayList.get(position).getNama();

        holder.txtNama.setText("Nama : " + nama);
        holder.txtUsername.setText("Username : " + dataModelArrayList.get(position).getUsername());
        holder.txtNoHP.setText("No : " + dataModelArrayList.get(position).getNo_hp());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDataPengajarListView dataPengajarListView = (DataPengajarListActivity) context;
                dataPengajarListView.showDialogDelete(
                        "" + kode,
                        "" + nama);
            }
        });

        String alamatFoto = baseUrl.getUrlUpload() + "image/pengajar/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamatFoto).resize(300, 600).centerInside().placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DataPengajarListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama, txtUsername, txtNoHP;
        protected ImageView ivFoto, ivDelete;

        public DataPengajarListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtNoHP = itemView.findViewById(R.id.txt_no_hp);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            if (statusActivity.equals("home->view->editPengajar")) {
                ivDelete.setVisibility(View.VISIBLE);
            }

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
        AdapterDataPengajarList.clickListener = clickListener;
    }
}
