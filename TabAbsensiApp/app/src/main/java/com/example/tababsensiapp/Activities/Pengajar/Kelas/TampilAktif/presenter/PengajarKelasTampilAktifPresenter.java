package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.view.IPengajarKelasTampilAktifView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pertemuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengajarKelasTampilAktifPresenter implements IPengajarKelasTampilAktifPresenter {

    Context context;
    IPengajarKelasTampilAktifView pengajarKelasTampilAktifView;

    BaseUrl baseUrl;

    ArrayList<Pertemuan> dataModelArrayList;

    public PengajarKelasTampilAktifPresenter(Context context, IPengajarKelasTampilAktifView pengajarKelasTampilAktifView) {
        this.context = context;
        this.pengajarKelasTampilAktifView = pengajarKelasTampilAktifView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/ambil_list_pertemuan_aktif"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_pertemuan_belum_selesai");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Pertemuan playerModel = new Pertemuan();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_pertemuan = dataobj.getString("id_pertemuan");

                                    String nama_pengajar = dataobj.getString("nama_pengajar");
                                    String nama_mata_pelajaran = dataobj.getString("nama_mata_pelajaran");

                                    String hari_btn = dataobj.getString("hari_btn");
                                    String waktu_mulai = dataobj.getString("waktu_mulai");
                                    String lokasi_mulai_la = dataobj.getString("lokasi_mulai_la");
                                    String lokasi_mulai_lo = dataobj.getString("lokasi_mulai_lo");

                                    String hari_jadwal = dataobj.getString("hari_jadwal");
                                    String jam_mulai = dataobj.getString("jam_mulai");
                                    String jam_berakhir = dataobj.getString("jam_berakhir");
                                    String harga_fee = dataobj.getString("harga_fee");

                                    playerModel.setId_pertemuan(id_pertemuan);

                                    playerModel.setNama_pengajar(nama_pengajar);
                                    playerModel.setNama_mata_pelajaran(nama_mata_pelajaran);

                                    playerModel.setHari_btn(hari_btn);
                                    playerModel.setWaktu_mulai(waktu_mulai);
                                    playerModel.setLokasi_mulai_la(lokasi_mulai_la);
                                    playerModel.setLokasi_mulai_lo(lokasi_mulai_lo);

                                    playerModel.setHari_jadwal(hari_jadwal);
                                    playerModel.setJam_mulai(jam_mulai);
                                    playerModel.setJam_berakhir(jam_berakhir);
                                    playerModel.setHarga_fee(harga_fee);

                                    dataModelArrayList.add(playerModel);
                                }

                                pengajarKelasTampilAktifView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                pengajarKelasTampilAktifView.onSetupListView(dataModelArrayList);
                                pengajarKelasTampilAktifView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarKelasTampilAktifView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarKelasTampilAktifView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
