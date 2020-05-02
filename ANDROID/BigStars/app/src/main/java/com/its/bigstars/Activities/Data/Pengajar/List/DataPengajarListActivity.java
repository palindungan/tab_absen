package com.its.bigstars.Activities.Data.Pengajar.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstars.Activities.Data.Pengajar.Add.DataPengajarAddActivity;
import com.its.bigstars.Activities.Data.Pengajar.List.presenter.DataPengajarListPresenter;
import com.its.bigstars.Activities.Data.Pengajar.List.presenter.IDataPengajarListPresenter;
import com.its.bigstars.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstars.Adapters.AdapterDataPengajarList;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Pengajar;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataPengajarListActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarListView {

    IDataPengajarListPresenter dataPengajarListPresenter;
    ToastMessage toastMessage;
    private AdapterDataPengajarList adapterDataPengajarList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public final static String EXTRA_STATUS_ACTIVITY = "EXTRA_STATUS_ACTIVITY";
    String status_activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_list);

        dataPengajarListPresenter = new DataPengajarListPresenter(this, this);
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        initActionBar();
        dataPengajarListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataPengajarListPresenter.onLoadDataList();

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

        status_activity = getIntent().getStringExtra(EXTRA_STATUS_ACTIVITY);
        if (!status_activity.equals("to_edit_pengajar")) {
            fab.hide();
        }

        fab.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), DataPengajarAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<Pengajar> dataModelArrayList) {

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
        dataPengajarListPresenter.onLoadDataList();
    }
}
