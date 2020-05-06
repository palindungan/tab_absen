package com.its.bigstars.Activities.Data.MataPelajaran.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataMataPelajaranEditPresenter implements IDataMataPelajaranEditPresenter {
    Context context;
    IDataMataPelajaranEditView dataMataPelajaranEditView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    public DataMataPelajaranEditPresenter(Context context, IDataMataPelajaranEditView dataMataPelajaranEditView) {
        this.context = context;
        this.dataMataPelajaranEditView = dataMataPelajaranEditView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onUpdate(String id_mata_pelajaran, String nama) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/update_mata_pelajaran"; // url http request

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
                                dataMataPelajaranEditView.backPressed();
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
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("nama", nama);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
