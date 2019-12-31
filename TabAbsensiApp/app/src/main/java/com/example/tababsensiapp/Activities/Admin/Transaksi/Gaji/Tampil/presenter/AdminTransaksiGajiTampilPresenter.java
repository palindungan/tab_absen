package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view.IAdminTransaksiGajiTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Pertemuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminTransaksiGajiTampilPresenter implements IAdminTransaksiGajiTampilPresenter {

    IAdminTransaksiGajiTampilView adminTransaksiGajiView;
    Context context;

    BaseUrl baseUrl;

    ArrayList<Pertemuan> dataModelArrayList;

    public AdminTransaksiGajiTampilPresenter(IAdminTransaksiGajiTampilView adminTransaksiGajiView, Context context) {
        this.adminTransaksiGajiView = adminTransaksiGajiView;
        this.context = context;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/transaksi/fee/ambil_data_pertemuan_for_fee"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");

                            String nama_pengajar = "kosong";
                            String total_pertemuan = "";
                            int total_harga_fee = 0;
                            String total = "";

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_pertemuan");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    total_pertemuan = String.valueOf(dataArray.length());

                                    Pertemuan playerModel = new Pertemuan();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_pertemuan = dataobj.getString("id_pertemuan");

                                    nama_pengajar = dataobj.getString("nama_pengajar");
                                    String nama_mata_pelajaran = dataobj.getString("nama_mata_pelajaran");

                                    String hari_btn = dataobj.getString("hari_btn");
                                    String waktu_mulai = dataobj.getString("waktu_mulai");
                                    String waktu_berakhir = dataobj.getString("waktu_berakhir");
                                    String lokasi_mulai_la = dataobj.getString("lokasi_mulai_la");
                                    String lokasi_mulai_lo = dataobj.getString("lokasi_mulai_lo");

                                    String hari_jadwal = dataobj.getString("hari_jadwal");
                                    String jam_mulai = dataobj.getString("jam_mulai");
                                    String jam_berakhir = dataobj.getString("jam_berakhir");
                                    String harga_fee = dataobj.getString("harga_fee");

                                    String status_fee = dataobj.getString("status_fee");
                                    String status_spp = dataobj.getString("status_spp");
                                    String status_pertemuan = dataobj.getString("status_pertemuan");
                                    String status_konfirmasi = dataobj.getString("status_konfirmasi");

                                    playerModel.setId_pertemuan(id_pertemuan);

                                    playerModel.setNama_pengajar(nama_pengajar);
                                    playerModel.setNama_mata_pelajaran(nama_mata_pelajaran);

                                    playerModel.setHari_btn(hari_btn);
                                    playerModel.setWaktu_mulai(waktu_mulai);
                                    playerModel.setWaktu_berakhir(waktu_berakhir);
                                    playerModel.setLokasi_mulai_la(lokasi_mulai_la);
                                    playerModel.setLokasi_mulai_lo(lokasi_mulai_lo);

                                    playerModel.setHari_jadwal(hari_jadwal);
                                    playerModel.setJam_mulai(jam_mulai);
                                    playerModel.setJam_berakhir(jam_berakhir);
                                    playerModel.setHarga_fee(harga_fee);

                                    playerModel.setStatus_fee(status_fee);
                                    playerModel.setStatus_spp(status_spp);
                                    playerModel.setStatus_pertemuan(status_pertemuan);
                                    playerModel.setStatus_konfirmasi(status_konfirmasi);

                                    dataModelArrayList.add(playerModel);

                                    total_harga_fee = total_harga_fee + Integer.parseInt(harga_fee);
                                }

                                total = String.valueOf(total_harga_fee);

                                adminTransaksiGajiView.onSetupListView(dataModelArrayList, nama_pengajar, total_pertemuan, total);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                adminTransaksiGajiView.onSetupListView(dataModelArrayList, nama_pengajar, total_pertemuan, total);
                                adminTransaksiGajiView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminTransaksiGajiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminTransaksiGajiView.onErrorMessage("Volley Error : " + error.toString());
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
    public void onBayar(String id_pengajar, String id_admin, String total_pertemuan, String total_harga_fee) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/transaksi/fee/tambah_transaksi"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");
                            String id_penggajian = obj.optString("id_penggajian");

                            if (obj.optString("success").equals("1")) {
                                adminTransaksiGajiView.onSuccessMessage(id_penggajian);

                                for (int i = 0; i < dataModelArrayList.size(); i++) {
                                    onBayarDetail(id_penggajian, dataModelArrayList.get(i).getId_pertemuan());
                                }

                            } else {
                                adminTransaksiGajiView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminTransaksiGajiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminTransaksiGajiView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                params.put("id_admin", id_admin);
                params.put("total_pertemuan", total_pertemuan);
                params.put("total_harga_fee", total_harga_fee);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBayarDetail(String id_penggajian, String id_pertemuan) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/transaksi/fee/tambah_detail_transaksi"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");

                            if (obj.optString("success").equals("1")) {
                                adminTransaksiGajiView.onSuccessMessage(message);
                            } else {
                                adminTransaksiGajiView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminTransaksiGajiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminTransaksiGajiView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_penggajian", id_penggajian);
                params.put("id_pertemuan", id_pertemuan);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
