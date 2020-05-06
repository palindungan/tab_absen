package com.its.bigstars.Activities.Data.MataPelajaran.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranEditView;
import com.its.bigstars.R;

public class DataMataPelajaranListActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_list);
    }

    @Override
    public void onClick(View v) {
        
    }
}
