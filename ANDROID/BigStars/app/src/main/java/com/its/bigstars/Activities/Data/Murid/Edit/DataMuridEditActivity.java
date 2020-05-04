package com.its.bigstars.Activities.Data.Murid.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Data.Murid.Edit.presenter.DataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.presenter.IDataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.view.IDataMuridEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridEditView {

    IDataMuridEditPresenter dataMuridEditPresenter;
    BaseUrl baseUrl;
    GlobalProcess globalProcess;
    ToastMessage toastMessage;

    Toolbar toolbar;
    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnNext;
    ImageView ivFoto;

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_NAMA_WALI_MURID = "EXTRA_NAMA_WALI_MURID";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    String id_murid, nama, nama_wali_murid, alamat, foto;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_edit);

        dataMuridEditPresenter = new DataMuridEditPresenter(this, this);
        baseUrl = new BaseUrl();
        globalProcess = new GlobalProcess();
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnNext = findViewById(R.id.btn_next);
        ivFoto = findViewById(R.id.iv_foto);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        nama_wali_murid = getIntent().getStringExtra(EXTRA_NAMA_WALI_MURID);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        initActionBar();
        inisiasiAwal(
                "" + id_murid,
                "" + nama,
                "" + nama_wali_murid,
                "" + alamat,
                "" + foto);

        ivFoto.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void initActionBar() {
    }

    private void inisiasiAwal(String id_murid, String nama, String nama_wali_murid, String alamat, String foto) {
    }

    @Override
    public void onClick(View v) {
    }
}
