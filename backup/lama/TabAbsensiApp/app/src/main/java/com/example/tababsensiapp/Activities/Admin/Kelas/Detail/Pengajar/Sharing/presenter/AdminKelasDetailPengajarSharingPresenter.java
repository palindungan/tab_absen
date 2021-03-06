package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.view.IAdminKelasDetailPengajarSharingView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminKelasDetailPengajarSharingPresenter implements IAdminKelasDetailPengajarSharingPresenter {

    Context context;
    IAdminKelasDetailPengajarSharingView adminKelasDetailPengajarSharingView;

    BaseUrl baseUrl;
    ArrayList<Pengajar> dataModelArrayList;

    public AdminKelasDetailPengajarSharingPresenter(Context context, IAdminKelasDetailPengajarSharingView adminKelasDetailPengajarSharingView) {
        this.context = context;
        this.adminKelasDetailPengajarSharingView = adminKelasDetailPengajarSharingView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/pengajar/list_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("pengajar");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Pengajar playerModel = new Pengajar();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_pengajar = dataobj.getString("id_pengajar");
                            String nama = dataobj.getString("nama");
                            String username = dataobj.getString("username");
                            String alamat = dataobj.getString("alamat");
                            String no_hp = dataobj.getString("no_hp");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_pengajar(id_pengajar);
                            playerModel.setNama(nama);
                            playerModel.setUsername(username);
                            playerModel.setAlamat(alamat);
                            playerModel.setNo_hp(no_hp);
                            playerModel.setFoto(foto);

                            dataModelArrayList.add(playerModel);

                        }

                        adminKelasDetailPengajarSharingView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminKelasDetailPengajarSharingView.onSetupListView(dataModelArrayList);
                        adminKelasDetailPengajarSharingView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminKelasDetailPengajarSharingView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminKelasDetailPengajarSharingView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onUpdate(String id_kelas_p, String id_sharing, String nama_sharing) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/update_sharing"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminKelasDetailPengajarSharingView.onSuccessMessage("Berhasil Sharing Kelas");
                                adminKelasDetailPengajarSharingView.backPressed();
                            } else {
                                adminKelasDetailPengajarSharingView.onErrorMessage("Gagal Sharing Kelas !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasDetailPengajarSharingView.onErrorMessage("Kesalahan  Sharing Kelas : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasDetailPengajarSharingView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kelas_p", id_kelas_p);
                params.put("id_sharing", id_sharing);
                params.put("nama_sharing", nama_sharing);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
