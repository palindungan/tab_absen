package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.view.IFormEditMataPelajaranView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormEditMataPelajaranPresenter implements IFormEditMataPelajaranPresenter {

    IFormEditMataPelajaranView formEditMataPelajaranView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormEditMataPelajaranPresenter(IFormEditMataPelajaranView formEditMataPelajaranView, Context context) {
        this.formEditMataPelajaranView = formEditMataPelajaranView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_mata_pelajaran) {
        String URLstring = base_url + "mata_pelajaran/ambil_data_mata_pelajaran"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
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

                                    formEditMataPelajaranView.setNilaiDefault(nama);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditMataPelajaranView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditMataPelajaranView.onErrorMessage("Volley Error : " + error.toString());
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
    public void onUpdateWaliMurid(String id_mata_pelajaran, String nama) {
        if (nama.isEmpty()) {
            formEditMataPelajaranView.onErrorMessage("Nama Tidak Boleh Kosong !");
        } else {

            String URLstring = base_url + "mata_pelajaran/update_mata_pelajaran"; // url http request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    formEditMataPelajaranView.onSucceessMessage("Berhasil Mengupdate Data");
                                    formEditMataPelajaranView.backPressed();
                                } else {
                                    formEditMataPelajaranView.onErrorMessage("Gagal Mengupdate Data !");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                formEditMataPelajaranView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            formEditMataPelajaranView.onErrorMessage("Volley Error : " + error.toString());
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
    }

    @Override
    public void hapusAkun(String id) {
        String URLstring = base_url + "mata_pelajaran/delete_mata_pelajaran"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                formEditMataPelajaranView.onSucceessMessage("Berhasil Menghapus Data");
                                formEditMataPelajaranView.backPressed();
                            } else {
                                formEditMataPelajaranView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditMataPelajaranView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditMataPelajaranView.onErrorMessage("Volley Error : " + error.toString());
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
