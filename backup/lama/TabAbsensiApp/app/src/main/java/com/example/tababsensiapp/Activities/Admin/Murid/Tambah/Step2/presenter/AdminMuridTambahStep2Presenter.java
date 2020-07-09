package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.view.IAdminMuridTambahStep2View;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminMuridTambahStep2Presenter implements IAdminMuridTambahStep2Presenter {

    Context context;
    IAdminMuridTambahStep2View adminMuridTambahStep2View;

    BaseUrl baseUrl;

    ArrayList<WaliMurid> dataModelArrayList;

    public AdminMuridTambahStep2Presenter(Context context, IAdminMuridTambahStep2View adminMuridTambahStep2View) {
        this.context = context;
        this.adminMuridTambahStep2View = adminMuridTambahStep2View;

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

                        adminMuridTambahStep2View.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminMuridTambahStep2View.onSetupListView(dataModelArrayList);
                        adminMuridTambahStep2View.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminMuridTambahStep2View.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminMuridTambahStep2View.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onSendData(String id_wali_murid, String nama, String foto) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/murid/tambah_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMuridTambahStep2View.onSuccessMessage("Berhasil Menambah Data Murid Baru");
                                adminMuridTambahStep2View.finish();
                            } else {
                                adminMuridTambahStep2View.onErrorMessage("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMuridTambahStep2View.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMuridTambahStep2View.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_wali_murid", id_wali_murid);
                params.put("nama", nama);
                params.put("foto", foto);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
