package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.view.IFormTambahWaliMuridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormTambahWaliMuridPresenter implements IFormTambahWaliMuridPresenter {
    IFormTambahWaliMuridView formTambahWaliMuridView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormTambahWaliMuridPresenter(IFormTambahWaliMuridView formTambahWaliMuridView, Context context) {
        this.formTambahWaliMuridView = formTambahWaliMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onSubmitWaliMurid(String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp) {

        if (nama.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("Nama Tidak Boleh Kosong !");
        } else if (username.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("Username Tidak Boleh Kosong !");
        } else if (password.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("Passowrd Tidak Boleh Kosong !");
        } else if (konfirmasi_password.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("Konfirmasi Password Tidak Boleh Kosong !");
        } else if (alamat.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("Alamat Tidak Boleh Kosong !");
        } else if (no_hp.isEmpty()) {
            formTambahWaliMuridView.onSubmitError("No Hp Tidak Boleh Kosong !");
        } else {

            if (password.equals(konfirmasi_password)) {
                String URLstring = base_url + "wali_murid/tambah_wali_murid"; // url http request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        formTambahWaliMuridView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                        formTambahWaliMuridView.backPressed();
                                    } else {
                                        formTambahWaliMuridView.onSubmitError("Gagal Menambah Data");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    formTambahWaliMuridView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                formTambahWaliMuridView.onSubmitError("Volley Error : " + error.toString());
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
            } else {
                formTambahWaliMuridView.onSubmitError("Kesalahan Konfirmasi Password !");
            }
        }

    }
}
