package com.example.tababsenapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Model.pengajar.Pengajar;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarPengajarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarPengajarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public DaftarPengajarViewHolder(@NonNull View itemView) {
            super(itemView);

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
