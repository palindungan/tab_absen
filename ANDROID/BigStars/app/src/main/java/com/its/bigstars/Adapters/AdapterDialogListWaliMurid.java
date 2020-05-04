package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDialogListWaliMurid extends RecyclerView.Adapter<AdapterDialogListWaliMurid.DialogListWaliMuridViewHolder> {

    Context context;
    ArrayList<WaliMurid> dataModelArrayList;

    private static ClickListener clickListener;

    BaseUrl baseUrl;
    SessionManager sessionManager;
    ToastMessage toastMessage;

    String statusActivity;

    public AdapterDialogListWaliMurid(Context context, ArrayList<WaliMurid> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        baseUrl = new BaseUrl();
        sessionManager = new SessionManager(context);
        toastMessage = new ToastMessage(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDialogListWaliMurid.DialogListWaliMuridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_dialog_list_wali_murid, parent, false);
        return new DialogListWaliMuridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDialogListWaliMurid.DialogListWaliMuridViewHolder holder, int position) {

        String nama = dataModelArrayList.get(position).getNama();

        holder.tvNama.setText(nama);
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage.onSuccessMessage(nama);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class DialogListWaliMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNama;

        public DialogListWaliMuridViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);

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
        AdapterDialogListWaliMurid.clickListener = clickListener;
    }
}
