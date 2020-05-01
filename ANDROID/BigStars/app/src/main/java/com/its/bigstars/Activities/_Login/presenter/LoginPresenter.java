package com.its.bigstars.Activities._Login.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities._Login.view.ILoginView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalValue;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {

    Context context;
    ILoginView loginView;

    BaseUrl baseUrl;
    GlobalValue globalValue;
    SessionManager sessionManager;
    ToastMessage toastMessage;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;

        baseUrl = new BaseUrl();
        globalValue = new GlobalValue();
        sessionManager = new SessionManager(context);
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onLogin(String username, String password, String hakAkses) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "login/masuk"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            JSONArray jsonArray = jsonObject.getJSONArray("data_result");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_user = object.getString("id_user").trim();
                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();

                                    // Intent intent = new Intent();

                                    if (hakAkses.equals("admin")) {

                                        // intent = new Intent(context, AdminHomeActivity.class);
                                        toastMessage.onSuccessMessage(id_user);

                                    } else if (hakAkses.equals("pengajar")) {

                                        // intent = new Intent(context, PengajarHomeActivity.class);
                                        toastMessage.onSuccessMessage(id_user);

                                    } else if (hakAkses.equals("wali_murid")) {

                                        toastMessage.onSuccessMessage(id_user);

                                    } else {
                                        sessionManager.logout();
                                    }

                                    // sessionManager.setSessionLogin(id_user, nama, username, hakAkses);

                                    // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    // context.startActivity(intent);
                                }

                            } else {
                                toastMessage.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            toastMessage.onErrorMessage(globalValue.getMessageResponseError() + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage.onErrorMessage(globalValue.getMessageConnectionError());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("hak_akses", hakAkses);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
