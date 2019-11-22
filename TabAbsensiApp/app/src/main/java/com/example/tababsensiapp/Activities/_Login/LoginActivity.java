package com.example.tababsensiapp.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities._Login.presenter.ILoginPresenter;
import com.example.tababsensiapp.Activities._Login.presenter.LoginPresenter;
import com.example.tababsensiapp.Activities._Login.view.ILoginView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    public static final String EXTRA_HAK_AKSES = "EXTRA_HAK_AKSES";
    String hakAkses;

    ILoginPresenter loginPresenter;

    EditText edtUsername, edtPassword;
    Button btnLogin;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hakAkses = getIntent().getStringExtra(EXTRA_HAK_AKSES);

        loginPresenter = new LoginPresenter(this, this);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        toolbar = findViewById(R.id.toolbar);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {

            boolean isEmpty = false;

            String inputUsername = edtUsername.getText().toString().trim();
            String inputPassword = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(inputUsername)) {
                isEmpty = true;
                edtUsername.setError("Isi Username Dengan Lengkap");
            } else if (TextUtils.isEmpty(inputPassword)) {
                isEmpty = true;
                edtPassword.setError("Isi Password Dengan Lengkap");
            }

            if (!isEmpty) {
                loginPresenter.onLogin(inputUsername, inputPassword, hakAkses);
            }
        }
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
