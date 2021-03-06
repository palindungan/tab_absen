package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Activities.Data.WaliMurid.List.DataWaliMuridListActivity;
import com.its.bigstars.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataWaliMuridList extends RecyclerView.Adapter<AdapterDataWaliMuridList.DataWaliMuridListViewHolder> {

    Context context;
    ArrayList<WaliMurid> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;
    ToastMessage toastMessage;

    String statusActivity;

    public AdapterDataWaliMuridList(Context context, ArrayList<WaliMurid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);
        toastMessage = new ToastMessage(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public DataWaliMuridListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_wali_murid_list, parent, false);
        return new DataWaliMuridListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataWaliMuridListViewHolder holder, int position) {

        String kode = dataModelArrayList.get(position).getId_wali_murid();
        String nama = dataModelArrayList.get(position).getNama();

        holder.txtNama.setText("Nama : " + dataModelArrayList.get(position).getNama());
        holder.txtUsername.setText("Username : " + dataModelArrayList.get(position).getUsername());
        holder.txtNoHp.setText("No. Hp : " + dataModelArrayList.get(position).getNo_hp());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDataWaliMuridListView dataWaliMuridListView = (DataWaliMuridListActivity) context;
                dataWaliMuridListView.showDialogDelete(
                        "" + kode,
                        "" + nama);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DataWaliMuridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama, txtUsername, txtNoHp;
        protected ImageView ivDelete;

        public DataWaliMuridListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtNoHp = itemView.findViewById(R.id.txt_no_hp);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            if (statusActivity.equals("home->view->editWaliMurid")) {
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
        AdapterDataWaliMuridList.clickListener = clickListener;
    }
}
