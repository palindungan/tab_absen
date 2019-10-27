package com.example.tababsenapp.Fitur.HalamanListPengajar.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanListPengajar.view.IListPengajarView;
import com.example.tababsenapp.Model.pengajar.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPengajarPresenter implements IListPengajarPresenter {

    IListPengajarView listPengajarView;
    Context context;

    SessionManager sessionManager;

    ArrayList<Pengajar> dataModelArrayList;

    public ListPengajarPresenter(IListPengajarView listPengajarView, Context context) {
        this.listPengajarView = listPengajarView;
        this.context = context;
    }

    @Override
    public void onLoadSemuaListPengajar() {

        sessionManager = new SessionManager(context);
        String base_url = sessionManager.getBaseUrl();
        String URLstring = base_url + "list_pengajar/pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("pengajar");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Pengajar playerModel = new Pengajar();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_pengajar = dataobj.getString("id_pengajar");
                            String nama = dataobj.getString("nama");
                            String username = dataobj.getString("username");
                            String alamat = dataobj.getString("alamat");
                            String no_hp = dataobj.getString("no_hp");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_pengajar(id_pengajar);
                            playerModel.setNama(nama);
                            playerModel.setUsername(username);
                            playerModel.setAlamat(alamat);
                            playerModel.setNo_hp(no_hp);
                            playerModel.setFoto(foto);

                            dataModelArrayList.add(playerModel);

                        }

                        listPengajarView.onSetupListView(dataModelArrayList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listPengajarView.onErrorMessage("Gagal Menerima Data : " + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listPengajarView.onErrorMessage("Volley Error : " + error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
