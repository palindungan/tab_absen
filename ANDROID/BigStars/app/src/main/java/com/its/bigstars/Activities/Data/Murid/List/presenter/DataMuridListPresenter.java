package com.its.bigstars.Activities.Data.Murid.List.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataMuridListPresenter implements IDataMuridListPresenter {

    Context context;
    IDataMuridListView dataMuridListView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    ArrayList<Murid> dataModelArrayList;

    public DataMuridListPresenter(Context context, IDataMuridListView dataMuridListView) {
        this.context = context;
        this.dataMuridListView = dataMuridListView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onLoadDataList() {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/murid/list_murid"; // url http request

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
                        dataMuridListView.onSetupListView(dataModelArrayList);

                    } else {
                        dataModelArrayList = new ArrayList<>();
                        dataMuridListView.onSetupListView(dataModelArrayList);
                        toastMessage.onErrorMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDelete(String id) {

    }
}
