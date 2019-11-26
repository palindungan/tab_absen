package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.view.IAdminMataPelajaranTambahView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminMataPelajaranTambahPresenter implements IAdminMataPelajaranTambahPresenter{

    Context context;
    IAdminMataPelajaranTambahView adminMataPelajaranTambahView;

    BaseUrl baseUrl;

    public AdminMataPelajaranTambahPresenter(Context context, IAdminMataPelajaranTambahView adminMataPelajaranTambahView) {
        this.context = context;
        this.adminMataPelajaranTambahView = adminMataPelajaranTambahView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onSubmit(String nama) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/mata_pelajaran/tambah_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMataPelajaranTambahView.onSubmitSuccess("Berhasil Menambah Data Mata Pelajaran Baru");
                                adminMataPelajaranTambahView.backPressed();
                            } else {
                                adminMataPelajaranTambahView.onSubmitError("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMataPelajaranTambahView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMataPelajaranTambahView.onSubmitError("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
