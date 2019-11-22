package com.example.tababsensiapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.MataPelajaran;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarMataPelajaran extends RecyclerView.Adapter<AdapterDaftarMataPelajaran.DaftarMataPelajaranViewHolder> {
    Context context;
    ArrayList<MataPelajaran> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;

    public AdapterDaftarMataPelajaran(Context context, ArrayList<MataPelajaran> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public DaftarMataPelajaranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_mata_pelajaran, parent, false);
        return new DaftarMataPelajaranViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarMataPelajaranViewHolder holder, int position) {
        holder.txtNama.setText(dataModelArrayList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarMataPelajaranViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNama;

        public DaftarMataPelajaranViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama);

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
        AdapterDaftarMataPelajaran.clickListener = clickListener;
    }
}
