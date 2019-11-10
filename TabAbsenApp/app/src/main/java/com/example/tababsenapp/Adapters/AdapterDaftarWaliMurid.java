package com.example.tababsenapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDaftarWaliMurid extends RecyclerView.Adapter<AdapterDaftarWaliMurid.DaftarWaliMuridViewHolder> {

    Context context;
    ArrayList<WaliMurid> dataModelArrayList;

    private static ClickListener clickListener;

    SessionManager sessionManager;

    public AdapterDaftarWaliMurid(Context context, ArrayList<WaliMurid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public DaftarWaliMuridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_wali_murid, parent, false);
        return new DaftarWaliMuridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarWaliMuridViewHolder holder, int position) {
        holder.edtNama.setText(dataModelArrayList.get(position).getNama());
        holder.edtUsername.setText(dataModelArrayList.get(position).getUsername());
        holder.edtNoHp.setText(dataModelArrayList.get(position).getNo_hp());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DaftarWaliMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected EditText edtNama, edtUsername, edtNoHp;

        public DaftarWaliMuridViewHolder(@NonNull View itemView) {
            super(itemView);

            edtNama = itemView.findViewById(R.id.edt_nama);
            edtUsername = itemView.findViewById(R.id.edt_username);
            edtNoHp = itemView.findViewById(R.id.edt_no_hp);

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
        AdapterDaftarWaliMurid.clickListener = clickListener;
    }
}
