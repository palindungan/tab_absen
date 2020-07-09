package com.example.tababsenapp.Fitur.HalamanUtama.presenter;

import com.example.tababsenapp.Fitur.HalamanUtama.view.IUtamaView;

public class UtamaPresenter implements IUtamaPresenter {

    IUtamaView utamaView;

    public UtamaPresenter(IUtamaView utamaView) {
        this.utamaView = utamaView;
    }

    @Override
    public void onMasukLogin(String hakAkses) {
        utamaView.onPindahHalaman(hakAkses);
    }
}
