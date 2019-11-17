package com.example.tababsenapp.Fitur.HalamanListMurid.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanListMurid.view.IListMuridView;
import com.example.tababsenapp.Model.murid.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMuridPresenter implements IListMuridPresenter {

    IListMuridView listMuridView;
    Context context;

    SessionManager sessionManager;
    ArrayList<Murid> dataModelArrayList;

    String base_url = "";

    public ListMuridPresenter(IListMuridView listMuridView, Context context) {
        this.listMuridView = listMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadSemuaListMurid() {
        base_url = sessionManager.getBaseUrl();
        String URLstring = base_url + "murid/list_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("murid");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Murid playerModel = new Murid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_murid = dataobj.getString("id_murid");
                            String nama = dataobj.getString("nama");
                            String nama_wali_murid = dataobj.getString("nama_wali_murid");
                            String alamat = dataobj.getString("alamat");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_murid(id_murid);
                            playerModel.setNama(nama);
                            playerModel.setNama_wali_murid(nama_wali_murid);
                            playerModel.setAlamat(alamat);
                            playerModel.setFoto(foto);

                            dataModelArrayList.add(playerModel);

                        }

                        listMuridView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        listMuridView.onSetupListView(dataModelArrayList);
                        listMuridView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listMuridView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listMuridView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
