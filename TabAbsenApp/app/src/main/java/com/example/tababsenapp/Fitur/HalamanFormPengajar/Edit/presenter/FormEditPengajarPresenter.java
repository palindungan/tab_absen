package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter;

import android.content.Context;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;

public class FormEditPengajarPresenter implements IFormEditPengajarPresenter {

    IFormEditPengajarView formEditPengajarView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormEditPengajarPresenter(IFormEditPengajarView formEditPengajarView, Context context) {
        this.formEditPengajarView = formEditPengajarView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {

    }
}
