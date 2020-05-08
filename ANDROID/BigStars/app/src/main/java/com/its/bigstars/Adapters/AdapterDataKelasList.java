package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Activities.Data.Kelas.List.DataKelasListActivity;
import com.its.bigstars.Activities.Data.Kelas.List.view.IDataKelasListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Kelas;
import com.its.bigstars.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataKelasList extends RecyclerView.Adapter<AdapterDataKelasList.DataKelasListViewHolder> {

    Context context;
    ArrayList<Kelas> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;
    ToastMessage toastMessage;

    String statusActivity;

    public AdapterDataKelasList(Context context, ArrayList<Kelas> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);
        toastMessage = new ToastMessage(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDataKelasList.DataKelasListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_kelas_list, parent, false);
        return new DataKelasListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataKelasList.DataKelasListViewHolder holder, int position) {

        String kode = dataModelArrayList.get(position).getId_kelas_p();
        String nama = dataModelArrayList.get(position).getNama_pelajaran();
        String jumlah_murid = dataModelArrayList.get(position).getJumlah_murid();

        holder.tvNamaPelajaran.setText(dataModelArrayList.get(position).getNama_pelajaran() + " (" + jumlah_murid + " Murid)");
        holder.tvHari.setText("Hari : " + dataModelArrayList.get(position).getHari());
        holder.tvJam.setText("Jam : " + dataModelArrayList.get(position).getJam_mulai() + " - " + dataModelArrayList.get(position).getJam_berakhir());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDataKelasListView dataKelasListView = (DataKelasListActivity) context;
                dataKelasListView.showDialogDelete(
                        "" + kode,
                        "" + nama);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DataKelasListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaPelajaran, tvHari, tvJam;
        protected ImageView ivDelete;

        public DataKelasListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelajaran = itemView.findViewById(R.id.tv_nama_pelajaran);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvJam = itemView.findViewById(R.id.tv_jam);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
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
        AdapterDataKelasList.clickListener = clickListener;
    }
}
