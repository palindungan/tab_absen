package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.presenter;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.view.IAdminKelasDetailMuridTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminKelasDetailMuridTampilPresenter implements IAdminKelasDetailMuridTampilPresenter {

    Context context;
    IAdminKelasDetailMuridTampilView adminKelasDetailMuridTampilView;

    BaseUrl baseUrl;

    ArrayList<Murid> dataModelArrayList;

    public AdminKelasDetailMuridTampilPresenter(Context context, IAdminKelasDetailMuridTampilView adminKelasDetailMuridTampilView) {
        this.context = context;
        this.adminKelasDetailMuridTampilView = adminKelasDetailMuridTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/murid/list_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("murid");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Murid playerModel = new Murid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_murid = dataobj.getString("id_murid");
                            String nama = dataobj.getString("nama");
                            String nama_wali_murid = dataobj.getString("nama_wali_murid");
                            String alamat = dataobj.getString("alamat");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_murid(id_murid);
                            playerModel.setNama(nama);
                            playerModel.setNama_wali_murid(nama_wali_murid);
                            playerModel.setAlamat(alamat);
                            playerModel.setFoto(foto);

                            dataModelArrayList.add(playerModel);

                        }

                        adminKelasDetailMuridTampilView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminKelasDetailMuridTampilView.onSetupListView(dataModelArrayList);
                        adminKelasDetailMuridTampilView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminKelasDetailMuridTampilView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminKelasDetailMuridTampilView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onSubmit(String id_murid, String id_kelas_p) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("Yakin Ingin Menambah Murid Ini?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menambah !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            String base_url = baseUrl.getUrlData();
                            String URL_DATA = base_url + "admin/kelas_pertemuan/tambah_murid"; // url http request

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                String success = jsonObject.getString("success");

                                                if (success.equals("1")) {
                                                    adminKelasDetailMuridTampilView.onSuccessMessage("Berhasil Menambah Data Baru");
                                                    adminKelasDetailMuridTampilView.backPressed();
                                                } else {
                                                    adminKelasDetailMuridTampilView.onErrorMessage("Gagal Menambah Data");
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                adminKelasDetailMuridTampilView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            adminKelasDetailMuridTampilView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id_murid", id_murid);
                                    params.put("id_kelas_p", id_kelas_p);
                                    return params;
                                }
                            };

                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            requestQueue.add(stringRequest);

                        } catch (Exception e) {
                            adminKelasDetailMuridTampilView.onErrorMessage("Terjadi Kesalahan Menambah Data " + e.toString());
                        }

                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
