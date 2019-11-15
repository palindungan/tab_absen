package com.example.tababsenapp.Fitur.HalamanListMurid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tababsenapp.Fitur.HalamanListMurid.view.IListMuridView;
import com.example.tababsenapp.Model.murid.Murid;
import com.example.tababsenapp.R;

import java.util.ArrayList;

public class HalamanListMuridActivity extends AppCompatActivity implements IListMuridView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_murid);
    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

    }

    @Override
    public void onSucceessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }
}
