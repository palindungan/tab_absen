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
import com.example.tababsensiapp.Models.Kelas;
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

    ArrayList<Murid> dataModelArrayListMurid;
    ArrayList<Kelas> dataModelArrayListKelas;

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
                                            adminKelasDetailKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
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

                            onLoadSemuaDataKelas(id);

                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayListMurid = new ArrayList<>();
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
                                    playerModel.setId_kelas_p(id_kelas_p);

                                    dataModelArrayListMurid.add(playerModel);
                                }

                                adminKelasDetailKelasView.onSetupListView(dataModelArrayListMurid);
                            } else {
                                dataModelArrayListMurid = new ArrayList<>();
                                adminKelasDetailKelasView.onSetupListView(dataModelArrayListMurid);
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
                        adminKelasDetailKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
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

    @Override
    public void onLoadSemuaDataKelas(String id) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/ambil_data_kelas_pertemuan_by_id_kelas_p"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayListKelas = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_kelas");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    String hari = dataobj.getString("hari");
                                    String jam_mulai = dataobj.getString("jam_mulai");
                                    String jam_berakhir = dataobj.getString("jam_berakhir");
                                    String harga_fee = dataobj.getString("harga_fee");
                                    String harga_spp = dataobj.getString("harga_spp");
                                    String nama_pelajaran = dataobj.getString("nama_pelajaran");
                                    String id_sharing = dataobj.getString("id_sharing");
                                    String nama_sharing = dataobj.getString("nama_sharing");
                                    String nama_pengajar = dataobj.getString("nama_pengajar");

                                    adminKelasDetailKelasView.setNilaiDefault(nama_pelajaran, nama_pengajar, harga_fee, harga_spp, hari, jam_mulai, jam_berakhir, id_sharing, nama_sharing);

                                }
                            } else {
                                adminKelasDetailKelasView.onErrorMessage("Gagal Mengambil Data !");
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
                        adminKelasDetailKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
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

    @Override
    public void onDeleteSharing(String id) {

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
                                adminKelasDetailKelasView.onSuccessMessage("Berhasil Menghapus Sharing Kelas");
                                adminKelasDetailKelasView.backPressed();
                            } else {
                                adminKelasDetailKelasView.onErrorMessage("Gagal Menghapus Sharing Kelas !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasDetailKelasView.onErrorMessage("Kesalahan Menghapus Sharing Kelas : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasDetailKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kelas_p", id);
                params.put("id_sharing", "null");
                params.put("nama_sharing", "kosong");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onMulaiPertemuan(String id_pengajar, String id_kelas_p, String lokasi_mulai_la, String lokasi_mulai_lo, String hari_kelas, String harga_fee, String harga_spp) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/tambah_absen"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {

                                String id_pertemuan_res = jsonObject.getString("id_pertemuan");

                                adminKelasDetailKelasView.onSuccessMessage(message);
                                adminKelasDetailKelasView.keHalamanAbsensi(id_pertemuan_res);
                            } else {
                                adminKelasDetailKelasView.onErrorMessage(message);
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
                        adminKelasDetailKelasView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                params.put("id_kelas_p", id_kelas_p);
                params.put("lokasi_mulai_la", lokasi_mulai_la);
                params.put("lokasi_mulai_lo", lokasi_mulai_lo);
                params.put("hari_kelas", hari_kelas);
                params.put("harga_fee", harga_fee);
                params.put("harga_spp", harga_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
