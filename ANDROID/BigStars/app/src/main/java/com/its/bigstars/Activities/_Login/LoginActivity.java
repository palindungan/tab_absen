package com.its.bigstars.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities._Login.view.ILoginView;
import com.its.bigstars.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View v) {

    }
}
