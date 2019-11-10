package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.view.IFormEditWaliMuridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormEditWaliMuridPresenter implements IFormEditWaliMuridPresenter {
    IFormEditWaliMuridView formEditWaliMuridView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormEditWaliMuridPresenter(IFormEditWaliMuridView formEditWaliMuridView, Context context) {
        this.formEditWaliMuridView = formEditWaliMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_wali_murid) {
        String URLstring = base_url + "wali_murid/ambil_data_wali_murid"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("wali_murid");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();
                                    String alamat = object.getString("alamat").trim();
                                    String no_hp = object.getString("no_hp").trim();

                                    formEditWaliMuridView.setNilaiDefault(nama, username, alamat, no_hp);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditWaliMuridView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditWaliMuridView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_wali_murid", id_wali_murid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onUpdateWaliMurid(String id_wali_murid, String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp) {
        if (nama.isEmpty()) {
            formEditWaliMuridView.onErrorMessage("Nama Tidak Boleh Kosong !");
        } else if (username.isEmpty()) {
            formEditWaliMuridView.onErrorMessage("Username Tidak Boleh Kosong !");
        } else if (alamat.isEmpty()) {
            formEditWaliMuridView.onErrorMessage("Alamat Tidak Boleh Kosong !");
        } else if (no_hp.isEmpty()) {
            formEditWaliMuridView.onErrorMessage("No Hp Tidak Boleh Kosong !");
        } else {

            if (password.equals(konfirmasi_password)) {
                String URLstring = base_url + "wali_murid/update_wali_murid"; // url http request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        formEditWaliMuridView.onSucceessMessage("Berhasil Mengupdate Data");
                                        formEditWaliMuridView.backPressed();
                                    } else {
                                        formEditWaliMuridView.onErrorMessage("Gagal Mengupdate Data !");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    formEditWaliMuridView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                formEditWaliMuridView.onErrorMessage("Volley Error : " + error.toString());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id_wali_murid", id_wali_murid);
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
                formEditWaliMuridView.onErrorMessage("Kesalahan Konfirmasi Password !");
            }
        }
    }

    @Override
    public void hapusAkun(String id) {
        String URLstring = base_url + "wali_murid/delete_wali_murid"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                formEditWaliMuridView.onSucceessMessage("Berhasil Menghapus Data");
                                formEditWaliMuridView.backPressed();
                            } else {
                                formEditWaliMuridView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditWaliMuridView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditWaliMuridView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
