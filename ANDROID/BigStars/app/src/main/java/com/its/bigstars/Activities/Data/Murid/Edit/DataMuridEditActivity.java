package com.its.bigstars.Activities.Data.Murid.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Data.Murid.Edit.presenter.DataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.presenter.IDataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.view.IDataMuridEditView;
import com.its.bigstars.Adapters.AdapterDialogListWaliMurid;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;

import java.util.ArrayList;

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

    private String[] myImageNameList = new String[]{
            "Benz", "Bike", "Car", "Carrera", "Ferrari", "Harly", "Lamborghini", "Silver"
    };
    public static Dialog dialog;

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

    private void showDialog() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list_wali_murid);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterDialogListWaliMurid adapterDialogListWaliMurid = new AdapterDialogListWaliMurid(this, myImageNameList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDialogListWaliMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDialogListWaliMurid.setOnItemClickListener(new AdapterDialogListWaliMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            showDialog();
        }
    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {
//        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
//        AdapterDialogListWaliMurid adapterDialogListWaliMurid = new AdapterDialogListWaliMurid(this, dataModelArrayList);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
//
//        recyclerView.setAdapter(adapterDialogListWaliMurid);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setNestedScrollingEnabled(true);
//
//        adapterDialogListWaliMurid.setOnItemClickListener(new AdapterDialogListWaliMurid.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//            }
//        });
//
//        dialog.show();
    }
}
