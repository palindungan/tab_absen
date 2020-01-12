package com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.view.IAdminWaliMuridTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminWaliMuridTampilPresenter implements IAdminWaliMuridTampilPresenter{

    Context context;
    IAdminWaliMuridTampilView adminWaliMuridTampilView;

    BaseUrl baseUrl;

    ArrayList<WaliMurid> dataModelArrayList;

    public AdminWaliMuridTampilPresenter(Context context, IAdminWaliMuridTampilView adminWaliMuridTampilView) {
        this.context = context;
        this.adminWaliMuridTampilView = adminWaliMuridTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/list_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("wali_murid");
                        for (int i = 0; i < dataArray.length(); i++) {

                            WaliMurid playerModel = new WaliMurid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_wali_murid = dataobj.getString("id_wali_murid");
                            String nama = dataobj.getString("nama");
                            String username = dataobj.getString("username");
                            String alamat = dataobj.getString("alamat");
                            String no_hp = dataobj.getString("no_hp");

                            playerModel.setId_wali_murid(id_wali_murid);
                            playerModel.setNama(nama);
                            playerModel.setUsername(username);
                            playerModel.setAlamat(alamat);
                            playerModel.setNo_hp(no_hp);

                            dataModelArrayList.add(playerModel);

                        }

                        adminWaliMuridTampilView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminWaliMuridTampilView.onSetupListView(dataModelArrayList);
                        adminWaliMuridTampilView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminWaliMuridTampilView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminWaliMuridTampilView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
