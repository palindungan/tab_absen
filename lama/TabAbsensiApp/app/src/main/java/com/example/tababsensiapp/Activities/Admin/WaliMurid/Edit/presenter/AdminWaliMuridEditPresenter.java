package com.example.tababsensiapp.Activities.Admin.WaliMurid.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Edit.view.IAdminWaliMuridEditView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminWaliMuridEditPresenter implements IAdminWaliMuridEditPresenter {

    Context context;
    IAdminWaliMuridEditView adminWaliMuridEditView;

    BaseUrl baseUrl;

    public AdminWaliMuridEditPresenter(Context context, IAdminWaliMuridEditView adminWaliMuridEditView) {
        this.context = context;
        this.adminWaliMuridEditView = adminWaliMuridEditView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_wali_murid) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/ambil_data_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
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

                                    adminWaliMuridEditView.setNilaiDefault(nama, username, alamat, no_hp);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminWaliMuridEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminWaliMuridEditView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
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
    public void onUpdate(String id_wali_murid, String nama, String username, String password, String alamat, String no_hp) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/update_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminWaliMuridEditView.onSuccessMessage("Berhasil Mengupdate Data");
                                adminWaliMuridEditView.backPressed();
                            } else {
                                adminWaliMuridEditView.onErrorMessage("Gagal Mengupdate Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminWaliMuridEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminWaliMuridEditView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
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

    }

    @Override
    public void hapusAkun(String id) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/delete_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminWaliMuridEditView.onSuccessMessage("Berhasil Menghapus Data");
                                adminWaliMuridEditView.backPressed();
                            } else {
                                adminWaliMuridEditView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminWaliMuridEditView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminWaliMuridEditView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
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
