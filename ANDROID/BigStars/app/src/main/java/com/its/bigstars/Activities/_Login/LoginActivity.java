package com.its.bigstars.Activities._Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.bigstars.Activities._Login.presenter.ILoginPresenter;
import com.its.bigstars.Activities._Login.presenter.LoginPresenter;
import com.its.bigstars.Activities._Login.view.ILoginView;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    ILoginPresenter loginPresenter;
    ToastMessage toastMessage;

    EditText edtUsername, edtPassword;
    Button btnLogin;
    Toolbar toolbar;

    public static final String EXTRA_HAK_AKSES = "EXTRA_HAK_AKSES";
    String hakAkses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this, this);
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        initActionBar();

        hakAkses = getIntent().getStringExtra(EXTRA_HAK_AKSES);

        btnLogin.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

                if (!hakAkses.equals("wali_murid")) {
                    loginPresenter.onLogin(inputUsername, inputPassword, hakAkses);
                } else {
                    toastMessage.onErrorMessage("Fitur Wali Murid Masih Belum Tersedia");
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
