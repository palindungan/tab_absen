package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PengajarAbsensiPertemuanPresenter implements IPengajarAbsensiPertemuanPresenter {

    Context context;
    IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public PengajarAbsensiPertemuanPresenter(Context context, IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView) {
        this.context = context;
        this.pengajarAbsensiPertemuanView = pengajarAbsensiPertemuanView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pertemuan) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/ambil_data_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("list_pertemuan");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_pengajar = object.getString("id_pengajar");

                                    String nama_pengajar = object.getString("nama_pengajar");
                                    String nama_mata_pelajaran = object.getString("nama_mata_pelajaran");

                                    String hari_btn = object.getString("hari_btn");
                                    String waktu_mulai = object.getString("waktu_mulai");
                                    String lokasi_mulai_la = object.getString("lokasi_mulai_la");
                                    String lokasi_mulai_lo = object.getString("lokasi_mulai_lo");

                                    String hari_jadwal = object.getString("hari_jadwal");
                                    String jam_mulai = object.getString("jam_mulai");
                                    String jam_berakhir = object.getString("jam_berakhir");
                                    String harga_fee = object.getString("harga_fee");

                                    HashMap<String, String> data = new HashMap<>();
                                    data.put("id_pertemuan", id_pertemuan);

                                    data.put("id_pengajar", id_pengajar);
                                    data.put("nama_pengajar", nama_pengajar);
                                    data.put("nama_mata_pelajaran", nama_mata_pelajaran);

                                    data.put("hari_btn", hari_btn);
                                    data.put("waktu_mulai", waktu_mulai);
                                    data.put("lokasi_mulai_la", lokasi_mulai_la);
                                    data.put("lokasi_mulai_lo", lokasi_mulai_lo);

                                    data.put("hari_jadwal", hari_jadwal);
                                    data.put("jam_mulai", jam_mulai);
                                    data.put("jam_berakhir", jam_berakhir);
                                    data.put("harga_fee", harga_fee);

                                    pengajarAbsensiPertemuanView.setNilaiDefault(data);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarAbsensiPertemuanView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarAbsensiPertemuanView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pertemuan", id_pertemuan);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void hapusData(String id) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/delete_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                pengajarAbsensiPertemuanView.onSuccessMessage(message);
                                pengajarAbsensiPertemuanView.backPressed();
                            } else {
                                pengajarAbsensiPertemuanView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarAbsensiPertemuanView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarAbsensiPertemuanView.onErrorMessage("Volley Error : " + error.toString());
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
