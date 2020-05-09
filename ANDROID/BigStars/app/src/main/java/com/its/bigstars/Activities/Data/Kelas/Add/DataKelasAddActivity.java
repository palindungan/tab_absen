package com.its.bigstars.Activities.Data.Kelas.Add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.its.bigstars.Activities.Data.Kelas.Add.presenter.DataKelasAddPresenter;
import com.its.bigstars.Activities.Data.Kelas.Add.presenter.IDataKelasAddPresenter;
import com.its.bigstars.Activities.Data.Kelas.Add.view.IDataKelasAddView;
import com.its.bigstars.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.R;

import java.util.ArrayList;
import java.util.Calendar;

public class DataKelasAddActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasAddView {

    IDataKelasAddPresenter dataKelasAddPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;

    AdapterDataMataPelajaranList adapterDataMataPelajaranList;

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtNamaPelajaran, edtHari, edtHargaFee, edtHargaSpp;
    Button btnPilih, btnJamMulai, btnJamBerakhir, btnSubmit;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    String id_pengajar, id_mata_pelajaran, nama_mata_pelajaran;
    String jam_mulai = "kosong";
    String jam_berakhir = "kosong";

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_add);

        dataKelasAddPresenter = new DataKelasAddPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();

        toolbar = findViewById(R.id.toolbar);
        edtNamaPelajaran = findViewById(R.id.edt_nama_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnPilih = findViewById(R.id.btn_pilih);
        btnJamMulai = findViewById(R.id.btn_jam_mulai);
        btnJamBerakhir = findViewById(R.id.btn_jam_berakhir);
        btnSubmit = findViewById(R.id.btn_submit);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        initActionBar();

        btnPilih.setOnClickListener(this);
        btnJamMulai.setOnClickListener(this);
        btnJamBerakhir.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputNamaPelajaran = edtNamaPelajaran.getText().toString().trim();
                        String inputHari = edtHari.getText().toString().trim();
                        String inputJamMulai = jam_mulai;
                        String inputJamBerakhir = jam_berakhir;
                        String inputHargaFee = edtHargaFee.getText().toString().trim();
                        String inputHargaSpp = edtHargaSpp.getText().toString().trim();

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputNamaPelajaran)) {
                            isEmpty = true;
                            edtNamaPelajaran.setError("Isi Data Dengan Lengkap");
                            toastMessage.onErrorMessage("Pilih Mata Pelajaran");
                        } else if (TextUtils.isEmpty(inputHari)) {
                            isEmpty = true;
                            edtHari.setError("Isi Data Dengan Lengkap");
                        } else if (inputJamMulai.equals("kosong")) {
                            isEmpty = true;
                            btnJamMulai.setError("Isi Data Dengan Lengkap");
                            toastMessage.onErrorMessage("Isi Jam Mulai Kelas");
                        } else if (inputJamBerakhir.equals("kosong")) {
                            isEmpty = true;
                            btnJamBerakhir.setError("Isi Data Dengan Lengkap");
                            toastMessage.onErrorMessage("Isi Jam Berakhir Kelas");
                        } else if (TextUtils.isEmpty(inputHargaFee)) {
                            isEmpty = true;
                            edtHargaFee.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputHargaSpp)) {
                            isEmpty = true;
                            edtHargaSpp.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                dataKelasAddPresenter.onSubmit(
                                        "" + id_pengajar,
                                        "" + id_mata_pelajaran,
                                        "" + inputHari,
                                        "" + inputJamMulai,
                                        "" + inputJamBerakhir,
                                        "" + inputHargaFee,
                                        "" + inputHargaSpp);
                            }

                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
                        }

                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showDialogPilih() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dataKelasAddPresenter.onLoadDataList();
    }

    private void showDialogTimePicker(Button btn, String kode) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String result = hourOfDay + ":" + minute;

                        btn.setText(result);

                        if (kode.equals("jam_mulai")) {
                            jam_mulai = result;
                        } else if (kode.equals("jam_berakhir")) {
                            jam_berakhir = result;
                        }

                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (v.getId() == R.id.btn_jam_mulai) {
            showDialogTimePicker(btnJamMulai, "jam_mulai");
        } else if (v.getId() == R.id.btn_jam_berakhir) {
            showDialogTimePicker(btnJamBerakhir, "jam_berakhir");
        } else if (v.getId() == R.id.btn_submit) {
            showDialog();
        }
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMataPelajaranList.setOnItemClickListener(new AdapterDataMataPelajaranList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
                nama_mata_pelajaran = dataModelArrayList.get(position).getNama();

                edtNamaPelajaran.setText(nama_mata_pelajaran);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void backPressed() {
        onBackPressed();
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
