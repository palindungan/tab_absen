package com.its.bigstars.Activities.Data.Murid.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstars.R;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_list);
    }

    @Override
    public void onClick(View v) {

    }
}
