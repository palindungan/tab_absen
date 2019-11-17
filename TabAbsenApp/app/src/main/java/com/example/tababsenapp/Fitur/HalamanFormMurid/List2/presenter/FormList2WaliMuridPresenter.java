package com.example.tababsenapp.Fitur.HalamanFormMurid.List2.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List2.view.IFormList2WaliMuridView;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormList2WaliMuridPresenter implements IFormList2WaliMuridPresenter {

    IFormList2WaliMuridView formList2WaliMuridView;
    Context context;

    SessionManager sessionManager;

    ArrayList<WaliMurid> dataModelArrayList;
    String base_url;

    public FormList2WaliMuridPresenter(IFormList2WaliMuridView formList2WaliMuridView, Context context) {
        this.formList2WaliMuridView = formList2WaliMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onLoadSemuaListWaliMurid() {
        String URLstring = base_url + "wali_murid/list_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring, new Response.Listener<String>() {
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

                        formList2WaliMuridView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        formList2WaliMuridView.onSetupListView(dataModelArrayList);
                        formList2WaliMuridView.onErrorMessage("Database Kosong Silahkan Mengupdate Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    formList2WaliMuridView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                formList2WaliMuridView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto) {
        String URLstring = base_url + "murid/update_murid"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                formList2WaliMuridView.onSuccessMessage("Berhasil Mengupdate Data Baru");
                                formList2WaliMuridView.stepFinish();
                            } else {
                                formList2WaliMuridView.onErrorMessage("Gagal Mengupdate Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formList2WaliMuridView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formList2WaliMuridView.onErrorMessage("Volley Error : " + error.toString());
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
