package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.view.IAdminTransaksiRiwayatGajiTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Penggajian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminTransaksiRiwayatGajiTampilPresenter implements IAdminTransaksiRiwayatGajiTampilPresenter {

    Context context;
    IAdminTransaksiRiwayatGajiTampilView adminTransaksiRiwayatGajiTampilView;

    ArrayList<Penggajian> dataModelArrayList;

    BaseUrl baseUrl;

    public AdminTransaksiRiwayatGajiTampilPresenter(Context context, IAdminTransaksiRiwayatGajiTampilView adminTransaksiRiwayatGajiTampilView) {
        this.context = context;
        this.adminTransaksiRiwayatGajiTampilView = adminTransaksiRiwayatGajiTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData(String id_pengajar) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/transaksi/fee/ambil_data_penggajian"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("penggajian");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Penggajian playerModel = new Penggajian();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_penggajian = dataobj.getString("id_penggajian");

                                    String id_pengajar = dataobj.getString("id_pengajar");
                                    String id_admin = dataobj.getString("id_admin");
                                    String waktu = dataobj.getString("waktu");
                                    String total_pertemuan = dataobj.getString("total_pertemuan");
                                    String total_harga_fee = dataobj.getString("total_harga_fee");

                                    playerModel.setId_penggajian(id_penggajian);

                                    playerModel.setId_pengajar(id_pengajar);
                                    playerModel.setId_admin(id_admin);
                                    playerModel.setWaktu(waktu);
                                    playerModel.setTotal_pertemuan(total_pertemuan);
                                    playerModel.setTotal_harga_fee(total_harga_fee);

                                    dataModelArrayList.add(playerModel);
                                }

                                adminTransaksiRiwayatGajiTampilView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                adminTransaksiRiwayatGajiTampilView.onSetupListView(dataModelArrayList);
                                adminTransaksiRiwayatGajiTampilView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminTransaksiRiwayatGajiTampilView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminTransaksiRiwayatGajiTampilView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
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
}
