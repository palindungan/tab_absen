package com.example.tababsenapp.Fitur.HalamanListMatapelajaran.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanListMatapelajaran.view.IListMataPelajaranView;
import com.example.tababsenapp.Model.mataPelajaran.MataPelajaran;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMataPelajaranPresenter implements IListMataPelajaranPresenter {

    IListMataPelajaranView listMataPelajaranView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    ArrayList<MataPelajaran> dataModelArrayList;

    public ListMataPelajaranPresenter(IListMataPelajaranView listMataPelajaranView, Context context) {
        this.listMataPelajaranView = listMataPelajaranView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }


    @Override
    public void onLoadSemuaListMataPelajaran() {
        String URLstring = base_url + "mata_pelajaran/list_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("strrrrr", ">>" + response);
                try {

                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("success").equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("mata_pelajaran");
                        for (int i = 0; i < dataArray.length(); i++) {

                            MataPelajaran playerModel = new MataPelajaran();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                            String nama = dataobj.getString("nama");

                            playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                            playerModel.setNama(nama);

                            dataModelArrayList.add(playerModel);

                        }

                        listMataPelajaranView.onSetupListView(dataModelArrayList);
                    } else {
                        dataModelArrayList = new ArrayList<>();
                        listMataPelajaranView.onSetupListView(dataModelArrayList);
                        listMataPelajaranView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listMataPelajaranView.onErrorMessage("Gagal Menerima Data : " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listMataPelajaranView.onErrorMessage("Volley Error : " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
