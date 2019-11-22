package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.view.IAdminMataPelajaranEditView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminMataPelajaranEditPresenter implements IAdminMataPelajaranEditPresenter {

    Context context;
    IAdminMataPelajaranEditView adminMataPelajaranEditView;

    BaseUrl baseUrl;

    public AdminMataPelajaranEditPresenter(Context context, IAdminMataPelajaranEditView adminMataPelajaranEditView) {
        this.context = context;
        this.adminMataPelajaranEditView = adminMataPelajaranEditView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_mata_pelajaran) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "mata_pelajaran/ambil_data_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("mata_pelajaran");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();

                                    adminMataPelajaranEditView.setNilaiDefault(nama);

                                }
                            } else {
                                adminMataPelajaranEditView.onErrorMessage("Gagal Inisialisasi Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMataPelajaranEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMataPelajaranEditView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onUpdate(String id_mata_pelajaran, String nama) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "mata_pelajaran/update_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMataPelajaranEditView.onSucceessMessage("Berhasil Mengupdate Data");
                                adminMataPelajaranEditView.backPressed();
                            } else {
                                adminMataPelajaranEditView.onErrorMessage("Gagal Mengupdate Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMataPelajaranEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMataPelajaranEditView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("nama", nama);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void hapusAkun(String id) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "mata_pelajaran/delete_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMataPelajaranEditView.onSucceessMessage("Berhasil Menghapus Data");
                                adminMataPelajaranEditView.backPressed();
                            } else {
                                adminMataPelajaranEditView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMataPelajaranEditView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMataPelajaranEditView.onErrorMessage("Volley Error : " + error.toString());
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
