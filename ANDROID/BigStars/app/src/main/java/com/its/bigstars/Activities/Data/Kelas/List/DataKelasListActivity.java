package com.its.bigstars.Activities.Data.Kelas.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstars.Activities.Data.Kelas.List.presenter.IDataKelasListPresenter;
import com.its.bigstars.Activities.Data.Kelas.List.view.IDataKelasListView;
import com.its.bigstars.Adapters.AdapterDataPengajarList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataKelasListActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasListView {

    IDataKelasListPresenter dataKelasListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataPengajarList adapterDataPengajarList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_list);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showDialogDelete(String kode, String nama) {

    }
}
