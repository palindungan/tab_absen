package com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.view.IAdminKelasTambahView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.MataPelajaran;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminKelasTambahPresenter implements IAdminKelasTambahPresenter {

    Context context;
    IAdminKelasTambahView adminKelasTambahView;

    BaseUrl baseUrl;
    ArrayList<MataPelajaran> dataModelArrayList;

    public AdminKelasTambahPresenter(Context context, IAdminKelasTambahView adminKelasTambahView) {
        this.context = context;
        this.adminKelasTambahView = adminKelasTambahView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onSubmit(String id_pengajar, String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/tambah_kelas_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminKelasTambahView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                adminKelasTambahView.backPressed();
                            } else {
                                adminKelasTambahView.onSubmitError("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasTambahView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasTambahView.onSubmitError("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("hari", hari);
                params.put("jam_mulai", jam_mulai);
                params.put("jam_berakhir", jam_berakhir);
                params.put("harga_fee", harga_fee);
                params.put("harga_spp", harga_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadSemuaData() {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/mata_pelajaran/list_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("mata_pelajaran");
                        for (int i = 0; i < dataArray.length(); i++) {

                            MataPelajaran playerModel = new MataPelajaran();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                            String nama = dataobj.getString("nama");

                            playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                            playerModel.setNama(nama);

                            dataModelArrayList.add(playerModel);

                        }

                        adminKelasTambahView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminKelasTambahView.onSetupListView(dataModelArrayList);
                        adminKelasTambahView.onSubmitError("Database Mata Pelajaran Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminKelasTambahView.onSubmitError("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminKelasTambahView.onSubmitError("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
