package com.its.bigstars.Activities.Data.Pengajar.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalValue;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataPengajarListPresenter implements IDataPengajarListPresenter {
    Context context;
    IDataPengajarListView dataPengajarListView;

    BaseUrl baseUrl;
    GlobalValue globalValue;
    ToastMessage toastMessage;

    ArrayList<Pengajar> dataModelArrayList;

    public DataPengajarListPresenter(Context context, IDataPengajarListView dataPengajarListView) {
        this.context = context;
        this.dataPengajarListView = dataPengajarListView;

        baseUrl = new BaseUrl();
        globalValue = new GlobalValue();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onLoadDataList() {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/pengajar/list_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);

                    String success = obj.getString("success");
                    String message = obj.getString("message");

                    if (success.equals("1")) {

                        dataModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("data_result");
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
                        dataPengajarListView.onSetupListView(dataModelArrayList);

                    } else {
                        dataModelArrayList = new ArrayList<>();
                        dataPengajarListView.onSetupListView(dataModelArrayList);
                        toastMessage.onErrorMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    toastMessage.onErrorMessage(globalValue.getMessageResponseError() + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage.onErrorMessage(globalValue.getMessageConnectionError());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
