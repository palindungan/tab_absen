package com.its.bigstars.Activities.Data.WaliMurid.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.WaliMurid.Edit.view.IDataWaliMuridEditView;
import com.its.bigstars.R;

public class DataWaliMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_edit);
    }

    @Override
    public void onClick(View v) {
        
    }
}
