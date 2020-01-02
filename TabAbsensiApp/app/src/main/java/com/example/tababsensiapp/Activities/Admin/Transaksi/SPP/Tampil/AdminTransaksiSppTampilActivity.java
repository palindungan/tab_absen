package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.presenter.IAdminTransaksiSppTampilPresenter;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasAktif;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.R;

public class AdminTransaksiSppTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiSppTampilPresenter {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    String id_bayar_spp = "";
    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    String id_murid = "";

    SessionManager sessionManager;

    IAdminTransaksiSppTampilPresenter adminTransaksiSppTampilPresenter;

    private AdapterPengajarDaftarKelasAktif adapterPengajarDaftarKelasAktif;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    TextView tvNamaMurid, tvTotalPertemuan, tvTotalSpp;
    Button btnBayarSpp;
    LinearLayout layoutKet;

    String id_admin, total_pertemuan, total_spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_spp);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void inisiasiAwal(String id_pengajar, String id_bayar_spp) {

    }

    @Override
    public void onBayar(String id_murid, String id_admin, String total_pertemuan, String total_spp) {

    }

    @Override
    public void onBayarDetail(String id_bayar_spp, String id_pertemuan) {

    }
}
