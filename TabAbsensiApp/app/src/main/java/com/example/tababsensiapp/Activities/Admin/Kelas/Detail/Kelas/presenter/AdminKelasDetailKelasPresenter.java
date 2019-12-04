package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter;

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
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminKelasDetailKelasPresenter implements IAdminKelasDetailKelasPresenter {

    Context context;
    IAdminKelasDetailKelasView adminKelasDetailKelasView;

    BaseUrl baseUrl;

    ArrayList<Murid> dataModelArrayList;

    public AdminKelasDetailKelasPresenter(Context context, IAdminKelasDetailKelasView adminKelasDetailKelasView) {
        this.context = context;
        this.adminKelasDetailKelasView = adminKelasDetailKelasView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void hapusAkun(String id_detail_kelas_p) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Data Ini ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            String base_url = baseUrl.getUrlData();
                            String URL_DATA = base_url + "admin/kelas_pertemuan/delete_detail_kelas_pertemuan"; // url http request

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                String success = jsonObject.getString("success");

                                                if (success.equals("1")) {
                                                    adminKelasDetailKelasView.onSuccessMessage("Berhasil Menghapus Data");
                                                    adminKelasDetailKelasView.backPressed();
                                                } else {
                                                    adminKelasDetailKelasView.onErrorMessage("Gagal Menghapus Data !");
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                adminKelasDetailKelasView.onErrorMessage("Kesalahan Menerima Data : " + response);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            adminKelasDetailKelasView.onErrorMessage("Volley Error : " + error.toString());
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id_detail_kelas_p", id_detail_kelas_p);
                                    return params;
                                }
                            };

                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            requestQueue.add(stringRequest);

                        } catch (Exception e) {
                            adminKelasDetailKelasView.onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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

    @Override
    public void onLoadSemuaData(String id) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/ambil_data_detail_kelas_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_murid_by_kelas");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Murid playerModel = new Murid();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_detail_kelas_p = dataobj.getString("id_detail_kelas_p");
                                    String id_murid = dataobj.getString("id_murid");
                                    String nama = dataobj.getString("nama");
                                    String id_wali_murid = dataobj.getString("id_wali_murid");
                                    String nama_wali_murid = dataobj.getString("nama_wali_murid");
                                    String alamat = dataobj.getString("alamat");
                                    String foto = dataobj.getString("foto");
                                    String id_kelas_p = dataobj.getString("id_kelas_p");

                                    playerModel.setId_detail_kelas_p(id_detail_kelas_p);
                                    playerModel.setId_murid(id_murid);
                                    playerModel.setNama(nama);
                                    playerModel.setId_wali_murid(id_wali_murid);
                                    playerModel.setNama_wali_murid(nama_wali_murid);
                                    playerModel.setAlamat(alamat);
                                    playerModel.setFoto(foto);
                                    playerModel.setFoto(foto);
                                    playerModel.setId_kelas_p(id_kelas_p);

                                    dataModelArrayList.add(playerModel);
                                }

                                adminKelasDetailKelasView.onSetupListView(dataModelArrayList);
                                adminKelasDetailKelasView.setNilaiDefault();
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                adminKelasDetailKelasView.onSetupListView(dataModelArrayList);
                                adminKelasDetailKelasView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasDetailKelasView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasDetailKelasView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kelas_p", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
