package com.its.bigstars.Activities.Data.Murid.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstars.Activities.Data.Murid.List.presenter.IDataMuridListPresenter;
import com.its.bigstars.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstars.Adapters.AdapterDataMuridList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

    IDataMuridListPresenter dataMuridListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataMuridList adapterDataMuridList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_list);
    }

    @Override
    public void onClick(View v) {

    }
}
