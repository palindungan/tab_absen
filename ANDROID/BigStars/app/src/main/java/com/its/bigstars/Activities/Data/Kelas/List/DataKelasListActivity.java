package com.its.bigstars.Activities.Data.Kelas.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Kelas.List.view.IDataKelasListView;
import com.its.bigstars.R;

public class DataKelasListActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasListView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_list);
    }

    @Override
    public void onClick(View v) {

    }
}
