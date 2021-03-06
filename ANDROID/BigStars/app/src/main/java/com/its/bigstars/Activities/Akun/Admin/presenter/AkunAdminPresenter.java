package com.its.bigstars.Activities.Akun.Admin.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Akun.Admin.view.IAkunAdminView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AkunAdminPresenter implements IAkunAdminPresenter {
    Context context;
    IAkunAdminView akunAdminView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    public AkunAdminPresenter(Context context, IAkunAdminView akunAdminView) {
        this.context = context;
        this.akunAdminView = akunAdminView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "akun/admin/update"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String message = jsonObject.getString("message");

                    if (success.equals("1")) {
                        toastMessage.onSuccessMessage(message);
                        akunAdminView.backPressed();
                    } else {
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_admin", id_admin);
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("foto", foto);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadData(String id_admin) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "akun/admin/list_admin"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    String success = obj.getString("success");
                    String message = obj.getString("message");

                    if (success.equals("1")) {

                        JSONArray dataArray = obj.getJSONArray("data_result");
                        for (int i = 0; i < dataArray.length(); i++) {

                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String nama = dataobj.getString("nama");
                            String username = dataobj.getString("username");
                            String foto = dataobj.getString("foto");

                            akunAdminView.setNilaiDefault("" + nama, "" + username, "" + foto);
                        }
                    } else {
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_admin", id_admin);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
