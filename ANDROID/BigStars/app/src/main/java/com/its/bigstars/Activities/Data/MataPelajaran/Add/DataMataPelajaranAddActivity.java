package com.its.bigstars.Activities.Data.MataPelajaran.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.MataPelajaran.Add.view.IDataMataPelajaranAddView;
import com.its.bigstars.R;

public class DataMataPelajaranAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranAddView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_add);
    }

    @Override
    public void onClick(View v) {
        
    }
}
