package com.example.tababsensiapp.Activities.Admin.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Home.View.IAdminHomeView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener , IAdminHomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
