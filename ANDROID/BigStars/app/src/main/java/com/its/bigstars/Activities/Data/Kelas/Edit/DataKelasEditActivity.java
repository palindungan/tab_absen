package com.its.bigstars.Activities.Data.Kelas.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Data.Kelas.Edit.view.IDataKelasEditView;
import com.its.bigstars.R;

public class DataKelasEditActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasEditView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_edit);
    }

    @Override
    public void onClick(View v) {

    }
}
