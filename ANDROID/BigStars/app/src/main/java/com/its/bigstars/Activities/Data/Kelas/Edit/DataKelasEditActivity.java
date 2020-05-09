package com.its.bigstars.Activities.Data.Kelas.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Kelas.Edit.view.IDataKelasEditView;
import com.its.bigstars.Models.Kelas;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataKelasEditActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasEditView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_PELAJARAN = "EXTRA_NAMA_PELAJARAN";
    public static final String EXTRA_HARI = "EXTRA_HARI";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_HARGA_SPP = "EXTRA_HARGA_SPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_edit);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {

    }

    @Override
    public void backPressed() {

    }
}
