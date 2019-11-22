package com.example.tababsensiapp.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities._Main.presenter.IMainPresenter;
import com.example.tababsensiapp.Activities._Main.presenter.MainPresenter;
import com.example.tababsensiapp.Activities._Main.view.IMainView;
import com.example.tababsensiapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainView {

    IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this, this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }
}
