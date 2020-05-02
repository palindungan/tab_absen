package com.its.bigstars.Activities.Data.Pengajar.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Pengajar.Add.View.IDataPengajarAddView;
import com.its.bigstars.R;

public class DataPengajarAddActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_add);
    }

    @Override
    public void onClick(View v) {
        
    }
}
