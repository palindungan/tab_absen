package com.its.bigstars.Activities.Data.WaliMurid.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstars.R;

public class DataWaliMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_list);
    }

    @Override
    public void onClick(View v) {

    }
}
