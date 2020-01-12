package com.example.tababsensiapp.Activities.Admin.WaliMurid.Tambah.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tambah.view.IAdminWaliMuridTambahView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminWaliMuridTambahPresenter implements IAdminWaliMuridTambahPresenter {

    Context context;
    IAdminWaliMuridTambahView adminWaliMuridTambahView;

    BaseUrl baseUrl;

    public AdminWaliMuridTambahPresenter(Context context, IAdminWaliMuridTambahView adminWaliMuridTambahView) {
        this.context = context;
        this.adminWaliMuridTambahView = adminWaliMuridTambahView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onSubmit(String nama, String username, String password, String alamat, String no_hp) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/tambah_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminWaliMuridTambahView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                adminWaliMuridTambahView.backPressed();
                            } else {
                                adminWaliMuridTambahView.onSubmitError("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminWaliMuridTambahView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminWaliMuridTambahView.onSubmitError("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
