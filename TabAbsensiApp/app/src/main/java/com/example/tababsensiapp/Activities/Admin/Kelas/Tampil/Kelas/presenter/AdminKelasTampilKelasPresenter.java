package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.presenter;

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
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view.IAdminKelasTampilKelasView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.Models.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminKelasTampilKelasPresenter implements IAdminKelasTampilKelasPresenter {

    Context context;
    IAdminKelasTampilKelasView adminKelasTampilKelasView;

    BaseUrl baseUrl;

    ArrayList<Kelas> dataModelArrayList;

    public AdminKelasTampilKelasPresenter(Context context, IAdminKelasTampilKelasView adminKelasTampilKelasView) {
        this.context = context;
        this.adminKelasTampilKelasView = adminKelasTampilKelasView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/ambil_data_kelas_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_kelas");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Kelas playerModel = new Kelas();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_kelas_p = dataobj.getString("id_kelas_p");
                                    String hari = dataobj.getString("hari");
                                    String jam_mulai = dataobj.getString("jam_mulai");
                                    String jam_berakhir = dataobj.getString("jam_berakhir");
                                    String harga_fee = dataobj.getString("harga_fee");
                                    String nama_pelajaran = dataobj.getString("nama_pelajaran");
                                    String nama_sharing = dataobj.getString("nama_sharing");

                                    String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                                    String id_pengajar = dataobj.getString("id_pengajar");
                                    String id_sharing = dataobj.getString("id_sharing");
                                    String nama_pengajar = dataobj.getString("nama_pengajar");

                                    String jumlah_murid = dataobj.getString("jumlah_murid");

                                    playerModel.setId_kelas_p(id_kelas_p);
                                    playerModel.setHari(hari);
                                    playerModel.setJam_mulai(jam_mulai);
                                    playerModel.setJam_berakhir(jam_berakhir);
                                    playerModel.setHarga_fee(harga_fee);
                                    playerModel.setNama_pelajaran(nama_pelajaran);
                                    playerModel.setNama_sharing(nama_sharing);

                                    playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                                    playerModel.setId_pengajar(id_pengajar);
                                    playerModel.setId_sharing(id_sharing);
                                    playerModel.setNama_pengajar(nama_pengajar);

                                    playerModel.setJumlah_murid(jumlah_murid);

                                    dataModelArrayList.add(playerModel);
                                }

                                adminKelasTampilKelasView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                adminKelasTampilKelasView.onSetupListView(dataModelArrayList);
                                adminKelasTampilKelasView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasTampilKelasView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasTampilKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void hapusAkun(String id_kelas_p) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Data Ini ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            String base_url = baseUrl.getUrlData();
                            String URL_DATA = base_url + "admin/kelas_pertemuan/delete_kelas_pertemuan"; // url http request

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                String success = jsonObject.getString("success");

                                                if (success.equals("1")) {
                                                    adminKelasTampilKelasView.onSuccessMessage("Berhasil Menghapus Data");
                                                    adminKelasTampilKelasView.backPressed();
                                                } else {
                                                    adminKelasTampilKelasView.onErrorMessage("Gagal Menghapus Data !");
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                adminKelasTampilKelasView.onErrorMessage("Kesalahan Menerima Data : " + response);
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            adminKelasTampilKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", id_kelas_p);
                                    return params;
                                }
                            };

                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            requestQueue.add(stringRequest);

                        } catch (Exception e) {
                            adminKelasTampilKelasView.onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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
