package com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.view.IPengajarAbsensiNextStepView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PengajarAbsensiNextStepPresenter implements IPengajarAbsensiNextStepPresenter {

    Context context;
    IPengajarAbsensiNextStepView pengajarAbsensiNextStepView;

    BaseUrl baseUrl;

    public PengajarAbsensiNextStepPresenter(Context context, IPengajarAbsensiNextStepView pengajarAbsensiNextStepView) {
        this.context = context;
        this.pengajarAbsensiNextStepView = pengajarAbsensiNextStepView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onAkhiriPertemuan(String id_pertemuan, String deskripsi, String lokasi_berakhir_la, String lokasi_berakhir_lo) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/absen/update_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {

                                pengajarAbsensiNextStepView.onSuccessMessage(message);
                                pengajarAbsensiNextStepView.keHalamanLain(id_pertemuan);
                            } else {
                                pengajarAbsensiNextStepView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarAbsensiNextStepView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarAbsensiNextStepView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pertemuan", id_pertemuan);
                params.put("deskripsi", deskripsi);
                params.put("lokasi_berakhir_la", lokasi_berakhir_la);
                params.put("lokasi_berakhir_lo", lokasi_berakhir_lo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
