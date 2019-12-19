package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PengajarAbsensiPertemuanPresenter implements IPengajarAbsensiPertemuanPresenter {

    Context context;
    IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public PengajarAbsensiPertemuanPresenter(Context context, IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView) {
        this.context = context;
        this.pengajarAbsensiPertemuanView = pengajarAbsensiPertemuanView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pertemuan) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/ambil_data_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("list_pertemuan");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_kelas_p = object.getString("id_kelas_p").trim();
                                    String id_mata_pelajaran = object.getString("id_mata_pelajaran").trim();
                                    String nama_mata_pelajaran = object.getString("nama_mata_pelajaran").trim();

                                    pengajarAbsensiPertemuanView.onSuccessMessage(id_kelas_p+id_mata_pelajaran+nama_mata_pelajaran);

                                    //HashMap<String, String> data = new HashMap<>();

                                    //pengajarAbsensiPertemuanView.setNilaiDefault(data);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarAbsensiPertemuanView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarAbsensiPertemuanView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pertemuan", id_pertemuan);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
