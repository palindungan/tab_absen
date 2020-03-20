package com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.view.IHomeAdminView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeAdminPresenter implements IHomeAdminPresenter {

    IHomeAdminView homeAdminView;
    Context context;

    SessionManager sessionManager;

    String base_url = "";

    public HomeAdminPresenter(IHomeAdminView homeAdminView, Context context) {
        this.homeAdminView = homeAdminView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }


    @Override
    public void onCount() {
        String URLstring = base_url + "home/admin/list_count"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        JSONArray dataArray = obj.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String pengajar = dataobj.getString("pengajar");
                            String murid = dataobj.getString("murid");
                            String wali_murid = dataobj.getString("wali_murid");
                            String mata_pelajaran = dataobj.getString("mata_pelajaran");

                            homeAdminView.setCountData(pengajar,murid,wali_murid,mata_pelajaran,"0","0");

                        }

                    } else {
                        homeAdminView.onErrorMessage("Terjadi Kesalahan !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    homeAdminView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                homeAdminView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
