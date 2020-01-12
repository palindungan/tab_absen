package com.example.tababsensiapp.Activities.Admin.Kelas.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Kelas.Edit.view.IAdminKelasEditHargaView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminKelasEditHargaPresenter implements IAdminKelasEditHargaPresenter {

    Context context;
    IAdminKelasEditHargaView adminKelasEditHargaView;

    BaseUrl baseUrl;

    public AdminKelasEditHargaPresenter(Context context, IAdminKelasEditHargaView adminKelasEditHargaView) {
        this.context = context;
        this.adminKelasEditHargaView = adminKelasEditHargaView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_kelas_p) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/ambil_data_kelas_pertemuan_by_id_kelas_p"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("list_kelas");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String harga_fee = object.getString("harga_fee").trim();
                                    String harga_spp = object.getString("harga_spp").trim();

                                    adminKelasEditHargaView.setNilaiDefault(harga_fee, harga_spp);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasEditHargaView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasEditHargaView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kelas_p", id_kelas_p);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onUpdate(String id_kelas_p, String harga_fee, String harga_spp) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/update_harga_kelas_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                adminKelasEditHargaView.onSuccessMessage(message);
                                adminKelasEditHargaView.backPressed();
                            } else {
                                adminKelasEditHargaView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminKelasEditHargaView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminKelasEditHargaView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kelas_p", id_kelas_p);
                params.put("harga_fee", harga_fee);
                params.put("harga_spp", harga_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
