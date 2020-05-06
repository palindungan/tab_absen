package com.its.bigstars.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;

import java.util.ArrayList;

public class AdapterDataMataPelajaranList extends RecyclerView.Adapter<AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder> {

    // extends RecyclerView.Adapter<AdapterDataWaliMuridList.DataWaliMuridListViewHolder
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
    }

    @NonNull
    @Override
    public AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DataMataPelajaranListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public DataMataPelajaranListViewHolder(@NonNull View itemView) {
            super(itemView);
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
