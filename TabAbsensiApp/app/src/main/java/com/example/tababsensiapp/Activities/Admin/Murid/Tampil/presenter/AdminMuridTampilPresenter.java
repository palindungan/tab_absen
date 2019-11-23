package com.example.tababsensiapp.Activities.Admin.Murid.Tampil.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.view.IAdminMuridTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminMuridTampilPresenter implements IAdminMuridTampilPresenter {

    Context context;
    IAdminMuridTampilView adminMuridTampilView;

    BaseUrl baseUrl;

    ArrayList<Murid> dataModelArrayList;
    String base_url = "";

    public AdminMuridTampilPresenter(Context context, IAdminMuridTampilView adminMuridTampilView) {
        this.context = context;
        this.adminMuridTampilView = adminMuridTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "murid/list_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("murid");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Murid playerModel = new Murid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_murid = dataobj.getString("id_murid");
                            String nama = dataobj.getString("nama");
                            String nama_wali_murid = dataobj.getString("nama_wali_murid");
                            String alamat = dataobj.getString("alamat");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_murid(id_murid);
                            playerModel.setNama(nama);
                            playerModel.setNama_wali_murid(nama_wali_murid);
                            playerModel.setAlamat(alamat);
                            playerModel.setFoto(foto);

                            dataModelArrayList.add(playerModel);

                        }

                        adminMuridTampilView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminMuridTampilView.onSetupListView(dataModelArrayList);
                        adminMuridTampilView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminMuridTampilView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminMuridTampilView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
