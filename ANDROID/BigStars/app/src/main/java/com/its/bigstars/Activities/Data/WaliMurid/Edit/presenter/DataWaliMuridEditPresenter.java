package com.its.bigstars.Activities.Data.WaliMurid.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.WaliMurid.Edit.view.IDataWaliMuridEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataWaliMuridEditPresenter implements IDataWaliMuridEditPresenter {

    Context context;
    IDataWaliMuridEditView dataWaliMuridEditView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    public DataWaliMuridEditPresenter(Context context, IDataWaliMuridEditView dataWaliMuridEditView) {
        this.context = context;
        this.dataWaliMuridEditView = dataWaliMuridEditView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onUpdate(String id_wali_murid, String nama, String username, String password, String alamat, String no_hp) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/wali_murid/update_wali_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                toastMessage.onSuccessMessage(message);
                                dataWaliMuridEditView.backPressed();
                            } else {
                                toastMessage.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_wali_murid", id_wali_murid);
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
