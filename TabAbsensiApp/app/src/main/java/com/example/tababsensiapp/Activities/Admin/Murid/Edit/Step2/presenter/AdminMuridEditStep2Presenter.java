package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.view.IAdminMuridEditStep2View;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminMuridEditStep2Presenter implements IAdminMuridEditStep2Presenter{

    Context context;
    IAdminMuridEditStep2View adminMuridEditStep2View;

    ArrayList<WaliMurid> dataModelArrayList;
    BaseUrl baseUrl;

    public AdminMuridEditStep2Presenter(Context context, IAdminMuridEditStep2View adminMuridEditStep2View) {
        this.context = context;
        this.adminMuridEditStep2View = adminMuridEditStep2View;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onLoadSemuaData() {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/wali_murid/list_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("wali_murid");
                        for (int i = 0; i < dataArray.length(); i++) {

                            WaliMurid playerModel = new WaliMurid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_wali_murid = dataobj.getString("id_wali_murid");
                            String nama = dataobj.getString("nama");
                            String username = dataobj.getString("username");
                            String alamat = dataobj.getString("alamat");
                            String no_hp = dataobj.getString("no_hp");

                            playerModel.setId_wali_murid(id_wali_murid);
                            playerModel.setNama(nama);
                            playerModel.setUsername(username);
                            playerModel.setAlamat(alamat);
                            playerModel.setNo_hp(no_hp);

                            dataModelArrayList.add(playerModel);
                        }

                        adminMuridEditStep2View.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        adminMuridEditStep2View.onSetupListView(dataModelArrayList);
                        adminMuridEditStep2View.onErrorMessage("Database Kosong Silahkan Mengupdate Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    adminMuridEditStep2View.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                adminMuridEditStep2View.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/murid/update_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMuridEditStep2View.onSuccessMessage("Berhasil Mengupdate Data Baru");
                                adminMuridEditStep2View.stepFinish();
                            } else {
                                adminMuridEditStep2View.onErrorMessage("Gagal Mengupdate Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMuridEditStep2View.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMuridEditStep2View.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_murid", id_murid);
                params.put("id_wali_murid", id_wali_murid);
                params.put("nama", nama);
                params.put("foto", foto);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
