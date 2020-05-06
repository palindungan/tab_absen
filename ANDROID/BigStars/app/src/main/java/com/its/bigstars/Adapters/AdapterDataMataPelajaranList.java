package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Activities.Data.MataPelajaran.List.DataMataPelajaranListActivity;
import com.its.bigstars.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataMataPelajaranList extends RecyclerView.Adapter<AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder> {

    Context context;
    ArrayList<MataPelajaran> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;
    ToastMessage toastMessage;

    String statusActivity;

    public AdapterDataMataPelajaranList(Context context, ArrayList<MataPelajaran> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);
        toastMessage = new ToastMessage(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_mata_pelajaran_list, parent, false);
        return new DataMataPelajaranListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder holder, int position) {
        String kode = dataModelArrayList.get(position).getId_mata_pelajaran();
        String nama = dataModelArrayList.get(position).getNama();

        holder.txtNama.setText(nama);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDataMataPelajaranListView dataMataPelajaranListView = (DataMataPelajaranListActivity) context;
                dataMataPelajaranListView.showDialogDelete(
                        "" + kode,
                        "" + nama);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DataMataPelajaranListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama;
        protected ImageView ivDelete;

        public DataMataPelajaranListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            if (statusActivity.equals("home->view->editMataPelajaran")) {
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
        AdapterDataMataPelajaranList.clickListener = clickListener;
    }
}
