package com.its.bigstars.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities._Main.view.IMainView;
import com.its.bigstars.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }
}
