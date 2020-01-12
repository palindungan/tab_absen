package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.view.IAdminKelasTampilPengajarView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminKelasTampilPengajarPresenter implements IAdminKelasTampilPengajarPresenter {
    Context context;
    IAdminKelasTampilPengajarView adminKelasTampilPengajarView;

    BaseUrl baseUrl;

    ArrayList<Pengajar> dataModelArrayList;

    public AdminKelasTampilPengajarPresenter(Context context, IAdminKelasTampilPengajarView adminKelasTampilPengajarView) {
        this.context = context;
        this.adminKelasTampilPengajarView = adminKelasTampilPengajarView;

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

                        adminKelasTampilPengajarView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminKelasTampilPengajarView.onSetupListView(dataModelArrayList);
                        adminKelasTampilPengajarView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminKelasTampilPengajarView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminKelasTampilPengajarView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
