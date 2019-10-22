package com.example.tababsenapp.Fitur.HalamanLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanLogin.presenter.ILoginPresenter;
import com.example.tababsenapp.Fitur.HalamanLogin.presenter.LoginPresenter;
import com.example.tababsenapp.Fitur.HalamanLogin.view.ILoginView;
import com.example.tababsenapp.R;

import es.dmoral.toasty.Toasty;

public class HalamanLoginActivity extends AppCompatActivity implements ILoginView {

    EditText edtUsername, edtPassword;
    Button btnLogin;

    ILoginPresenter loginPresenter;

    String EXTRA_HAK_AKSES = "EXTRA_HAK_AKSES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        loginPresenter = new LoginPresenter(this,this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                String hakAkses = getIntent().getStringExtra(EXTRA_HAK_AKSES);
                loginPresenter.onLogin(username, password, hakAkses);
            }
        });
    }

    @Override
    public void onLoginSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
