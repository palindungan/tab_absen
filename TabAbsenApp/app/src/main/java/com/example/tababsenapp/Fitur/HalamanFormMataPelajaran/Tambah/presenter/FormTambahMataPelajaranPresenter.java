package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Tambah.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Tambah.view.IFormTambahMataPelajaranView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormTambahMataPelajaranPresenter implements IFormTambahMataPelajaranPresenter {

    IFormTambahMataPelajaranView formTambahMataPelajaranView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormTambahMataPelajaranPresenter(IFormTambahMataPelajaranView formTambahMataPelajaranView, Context context) {
        this.formTambahMataPelajaranView = formTambahMataPelajaranView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onSubmitMataPelajaran(String nama) {

        if (nama.isEmpty()) {
            formTambahMataPelajaranView.onSubmitError("Isi Nama Mata Pelajaran !");
        } else {

            String URLstring = base_url + "mata_pelajaran/tambah_mata_pelajaran"; // url http request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    formTambahMataPelajaranView.onSubmitSuccess("Berhasil Menambah Data Mata Pelajaran Baru");
                                    formTambahMataPelajaranView.backPressed();
                                } else {
                                    formTambahMataPelajaranView.onSubmitError("Gagal Menambah Data");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                formTambahMataPelajaranView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            formTambahMataPelajaranView.onSubmitError("Volley Error : " + error.toString());
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
}
