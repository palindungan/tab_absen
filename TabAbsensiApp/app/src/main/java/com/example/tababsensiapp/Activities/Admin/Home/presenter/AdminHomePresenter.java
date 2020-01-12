package com.example.tababsensiapp.Activities.Admin.Home.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Home.View.IAdminHomeView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminHomePresenter implements IAdminHomePresenter {

    Context context;
    IAdminHomeView adminHomeView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public AdminHomePresenter(Context context, IAdminHomeView adminHomeView) {
        this.context = context;
        this.adminHomeView = adminHomeView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }

//    @Override
//    public void onCount() {
//
//        String base_url = baseUrl.getUrlData();
//        String URL_DATA = base_url + "admin/home/list_count"; // url http request
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // Log.d("strrrrr", ">>" + response);
//                try {
//
//                    JSONObject obj = new JSONObject(response);
//
//                    if (obj.optString("success").equals("1")) {
//
//                        JSONArray dataArray = obj.getJSONArray("data");
//                        for (int i = 0; i < dataArray.length(); i++) {
//
//                            JSONObject dataobj = dataArray.getJSONObject(i);
//
//                            String pengajar = dataobj.getString("pengajar");
//                            String murid = dataobj.getString("murid");
//                            String wali_murid = dataobj.getString("wali_murid");
//                            String mata_pelajaran = dataobj.getString("mata_pelajaran");
//
//                            adminHomeView.setCountData(pengajar,murid,wali_murid,mata_pelajaran,"0","0");
//
//                        }
//
//                    } else {
//                        adminHomeView.onErrorMessage("Terjadi Kesalahan !");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    adminHomeView.onErrorMessage("Gagal Menerima Data : " + e.toString());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                adminHomeView.onErrorMessage("Volley Error : " + error.toString());
//            }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        requestQueue.add(stringRequest);
//
//    }
}
