package com.its.bigstars.Activities.Data.Pengajar.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstars.R;

public class DataPengajarListActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_list);
    }

    @Override
    public void onClick(View v) {
        
    }
}
