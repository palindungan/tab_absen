package com.its.bigstars.Activities.Data.Kelas.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Kelas.Add.view.IDataKelasAddView;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataKelasAddActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_add);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void backPressed() {

    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {

    }
}
