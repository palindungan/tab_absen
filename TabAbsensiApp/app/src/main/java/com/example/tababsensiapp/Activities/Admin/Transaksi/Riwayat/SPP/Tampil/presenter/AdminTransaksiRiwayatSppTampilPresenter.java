package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.view.IAdminTransaksiRiwayatSppTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.BayarSpp;
import com.example.tababsensiapp.Models.Penggajian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminTransaksiRiwayatSppTampilPresenter implements IAdminTransaksiRiwayatSppTampilPresenter {

    Context context;
    IAdminTransaksiRiwayatSppTampilView adminTransaksiRiwayatSppTampilView;

    ArrayList<BayarSpp> dataModelArrayList;

    BaseUrl baseUrl;

    public AdminTransaksiRiwayatSppTampilPresenter(Context context, IAdminTransaksiRiwayatSppTampilView adminTransaksiRiwayatSppTampilView) {
        this.context = context;
        this.adminTransaksiRiwayatSppTampilView = adminTransaksiRiwayatSppTampilView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData(String id_wali_murid) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/transaksi/spp/ambil_data_bayar_spp"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String message = obj.optString("message");

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("bayar_spp");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    BayarSpp playerModel = new BayarSpp();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_bayar_spp = dataobj.getString("id_bayar_spp");

                                    String id_wali_murid = dataobj.getString("id_wali_murid");
                                    String id_admin = dataobj.getString("id_admin");
                                    String waktu = dataobj.getString("waktu");
                                    String total_pertemuan = dataobj.getString("total_pertemuan");
                                    String total_spp = dataobj.getString("total_spp");

                                    playerModel.setId_bayar_spp(id_bayar_spp);

                                    playerModel.setId_wali_murid(id_wali_murid);
                                    playerModel.setId_admin(id_admin);
                                    playerModel.setWaktu(waktu);
                                    playerModel.setTotal_pertemuan(total_pertemuan);
                                    playerModel.setTotal_spp(total_spp);

                                    dataModelArrayList.add(playerModel);
                                }

                                adminTransaksiRiwayatSppTampilView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                adminTransaksiRiwayatSppTampilView.onSetupListView(dataModelArrayList);
                                adminTransaksiRiwayatSppTampilView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminTransaksiRiwayatSppTampilView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminTransaksiRiwayatSppTampilView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_wali_murid", id_wali_murid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
