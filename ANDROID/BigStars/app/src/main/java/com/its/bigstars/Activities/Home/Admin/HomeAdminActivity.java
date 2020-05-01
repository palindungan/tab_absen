package com.its.bigstars.Activities.Home.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Home.Admin.view.IHomeAdminView;
import com.its.bigstars.R;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener, IHomeAdminView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    @Override
    public void onClick(View v) {

    }
}
