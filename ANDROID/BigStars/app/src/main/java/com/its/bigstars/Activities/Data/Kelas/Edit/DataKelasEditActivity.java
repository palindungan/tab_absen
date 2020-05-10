package com.its.bigstars.Activities.Data.Kelas.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstars.Activities.Data.Kelas.Edit.presenter.DataKelasEditPresenter;
import com.its.bigstars.Activities.Data.Kelas.Edit.presenter.IDataKelasEditPresenter;
import com.its.bigstars.Activities.Data.Kelas.Edit.view.IDataKelasEditView;
import com.its.bigstars.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstars.Adapters.AdapterDataMuridList;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Kelas;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.Models.Murid;
import com.its.bigstars.R;

import java.util.ArrayList;
import java.util.Calendar;

public class DataKelasEditActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasEditView {

    IDataKelasEditPresenter dataKelasEditPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;
    SessionManager sessionManager;

    AdapterDataMataPelajaranList adapterDataMataPelajaranList;
    AdapterDataMuridList adapterDataMuridList;

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtNamaPelajaran, edtHari, edtHargaFee, edtHargaSpp;
    Button btnPilih, btnJamMulai, btnJamBerakhir, btnUpdate;
    TextView tvStatus;
    ImageButton btnSharing, btnDeleteSharing;

    FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_PELAJARAN = "EXTRA_NAMA_PELAJARAN";
    public static final String EXTRA_HARI = "EXTRA_HARI";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_HARGA_SPP = "EXTRA_HARGA_SPP";

    String statusActivity;

    String id_kelas_p, id_pengajar, id_mata_pelajaran, nama_pelajaran, hari, jam_mulai, jam_berakhir, harga_fee, harga_spp;
    String status_kelas;

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_edit);

        dataKelasEditPresenter = new DataKelasEditPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        edtNamaPelajaran = findViewById(R.id.edt_nama_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnPilih = findViewById(R.id.btn_pilih);
        btnJamMulai = findViewById(R.id.btn_jam_mulai);
        btnJamBerakhir = findViewById(R.id.btn_jam_berakhir);
        btnUpdate = findViewById(R.id.btn_update);
        tvStatus = findViewById(R.id.tv_status);
        btnSharing = findViewById(R.id.btn_sharing);
        btnDeleteSharing = findViewById(R.id.btn_delete_sharing);

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama_pelajaran = getIntent().getStringExtra(EXTRA_NAMA_PELAJARAN);
        hari = getIntent().getStringExtra(EXTRA_HARI);
        jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
        jam_berakhir = getIntent().getStringExtra(EXTRA_JAM_BERAKHIR);
        harga_fee = getIntent().getStringExtra(EXTRA_HARGA_FEE);
        harga_spp = getIntent().getStringExtra(EXTRA_HARGA_SPP);

        status_kelas = tvStatus.getText().toString().trim();

        initActionBar();
        inisiasiAwal(
                "" + id_pengajar,
                "" + id_mata_pelajaran,
                "" + nama_pelajaran,
                "" + hari,
                "" + jam_mulai,
                "" + jam_berakhir,
                "" + harga_fee,
                "" + harga_spp);

        dataKelasEditPresenter.onLoadDataListMurid(id_kelas_p);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataKelasEditPresenter.onLoadDataListMurid(id_kelas_p);

                // CallYourRefreshingMethod();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            btnPilih.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);

            fab.show();

            if (status_kelas.equals("Status : Tidak Dibagikan")) {
                btnSharing.setVisibility(View.VISIBLE);
            } else {
                btnDeleteSharing.setVisibility(View.VISIBLE);
            }

            edtHari.setFocusableInTouchMode(true);
            edtHargaFee.setFocusableInTouchMode(true);
            edtHargaSpp.setFocusableInTouchMode(true);

            edtHari.setFocusable(true);
            edtHargaFee.setFocusable(true);
            edtHargaSpp.setFocusable(true);

            btnJamMulai.setOnClickListener(this);
            btnJamBerakhir.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
            fab.setOnClickListener(this);
        }

        btnPilih.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inisiasiAwal(String id_pengajar, String id_mata_pelajaran, String nama_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp) {
        edtNamaPelajaran.setText(nama_pelajaran);
        edtHari.setText(hari);
        btnJamMulai.setText(jam_mulai);
        btnJamBerakhir.setText(jam_berakhir);
        edtHargaFee.setText(harga_fee);
        edtHargaSpp.setText(harga_spp);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data ?");
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
                                dataKelasEditPresenter.onUpdate(
                                        "" + id_kelas_p,
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
        dataKelasEditPresenter.onLoadDataListPelajaran();
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

    private void showDialogPilihMurid() {
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
        dataKelasEditPresenter.onLoadDataListSemuaMurid();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (v.getId() == R.id.btn_jam_mulai) {
            showDialogTimePicker(btnJamMulai, "jam_mulai");
        } else if (v.getId() == R.id.btn_jam_berakhir) {
            showDialogTimePicker(btnJamBerakhir, "jam_berakhir");
        } else if (v.getId() == R.id.btn_update) {
            showDialog();
        } else if (v.getId() == R.id.fab) {
            showDialogPilihMurid();
        }
    }

    @Override
    public void onSetupListViewPelajaranDialog(ArrayList<MataPelajaran> dataModelArrayList) {
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
                nama_pelajaran = dataModelArrayList.get(position).getNama();

                edtNamaPelajaran.setText(nama_pelajaran);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onSetupListViewMuridDialog(ArrayList<Murid> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMuridList.setOnItemClickListener(new AdapterDataMuridList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onSetupListViewMurid(ArrayList<Murid> dataModelArrayList) {
        recyclerView = findViewById(R.id.recycle_view);
        adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataMuridList.setOnItemClickListener(new AdapterDataMuridList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {

                } else if (statusActivity.equals("xx->view->yy")) {

                }
            }
        });
    }

    @Override
    public void showDialogDeleteMurid(String id_detail_kelas_p, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Data " + nama + " ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            dataKelasEditPresenter.onDeleteMurid(id_detail_kelas_p);
                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public void onRefreshDataListMurid() {
        dataKelasEditPresenter.onLoadDataListMurid(id_kelas_p);
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

    @Override
    protected void onResume() {
        super.onResume();
        dataKelasEditPresenter.onLoadDataListMurid(id_kelas_p);
    }
}
