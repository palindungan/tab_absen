package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.view.IAdminMataPelajaranTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.MataPelajaran;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminMataPelajaranTampilPresenter implements IAdminMataPelajaranTampilPresenter {

    Context context;
    IAdminMataPelajaranTampilView adminMataPelajaranTampilView;

    BaseUrl baseUrl;

    ArrayList<MataPelajaran> dataModelArrayList;

    public AdminMataPelajaranTampilPresenter(Context context, IAdminMataPelajaranTampilView adminMataPelajaranTampilView) {
        this.context = context;
        this.adminMataPelajaranTampilView = adminMataPelajaranTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "mata_pelajaran/list_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("mata_pelajaran");
                        for (int i = 0; i < dataArray.length(); i++) {

                            MataPelajaran playerModel = new MataPelajaran();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                            String nama = dataobj.getString("nama");

                            playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                            playerModel.setNama(nama);

                            dataModelArrayList.add(playerModel);

                        }

                        adminMataPelajaranTampilView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminMataPelajaranTampilView.onSetupListView(dataModelArrayList);
                        adminMataPelajaranTampilView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminMataPelajaranTampilView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminMataPelajaranTampilView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
