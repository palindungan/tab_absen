package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Detail.view.IAdminKelasDetailMuridDetailView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class AdminKelasDetailMuridDetailActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailMuridDetailView {

    BaseUrl baseUrl;

    public static String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static String EXTRA_NAMA = "EXTRA_NAMA";
    public static String EXTRA_NAMA_WALI_MURID = "EXTRA_NAMA_WALI_MURID";
    public static String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static String EXTRA_FOTO = "EXTRA_FOTO";

    String id_murid, id_wali_murid, nama, nama_wali_murid, alamat, foto = "";

    Toolbar toolbar;

    ImageView ivFoto;
    TextView tvNama, tvNamaWaliMurid, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_murid_detail);

        baseUrl = new BaseUrl();

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        nama_wali_murid = getIntent().getStringExtra(EXTRA_NAMA_WALI_MURID);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        ivFoto = findViewById(R.id.iv_foto);
        tvNama = findViewById(R.id.tv_nama);
        tvNamaWaliMurid = findViewById(R.id.tv_nama_wali_murid);
        tvAlamat = findViewById(R.id.tv_alamat);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();
        setNilaiDefault();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setNilaiDefault() {
        String alamat_foto = baseUrl.getUrlUpload() + "image/murid/" + foto + ".jpg";

        tvNama.setText(nama);
        tvNamaWaliMurid.setText(nama_wali_murid);
        tvAlamat.setText(alamat);
        Picasso.get().load(alamat_foto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
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
