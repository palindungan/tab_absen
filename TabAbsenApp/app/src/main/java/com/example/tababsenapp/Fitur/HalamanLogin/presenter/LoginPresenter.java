package com.example.tababsenapp.Fitur.HalamanLogin.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.HalamanHomeAdminActivity;
import com.example.tababsenapp.Fitur.HalamanHome.Pengajar.HalamanHomePengajarActivity;
import com.example.tababsenapp.Fitur.HalamanHome.WaliMurid.HalamanHomeWaliMuridActivity;
import com.example.tababsenapp.Fitur.HalamanLogin.view.ILoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;
    Context context;

    SessionManager sessionManager;

    public LoginPresenter(ILoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void onLogin(final String username, final String password, final String hakAkses) {

        sessionManager = new SessionManager(context);
        String base_url = sessionManager.getBaseUrl();
        String URL_LOGIN = base_url + "login/" + hakAkses; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_user = "";
                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();

                                    Intent intent = new Intent();

                                    if (hakAkses.equals("wali_murid")) {

                                        id_user = object.getString("id_wali_murid").trim();
                                        intent = new Intent(context, HalamanHomeWaliMuridActivity.class);

                                    } else if (hakAkses.equals("pengajar")) {

                                        id_user = object.getString("id_pengajar").trim();
                                        intent = new Intent(context, HalamanHomePengajarActivity.class);

                                    } else if (hakAkses.equals("admin")) {

                                        id_user = object.getString("id_admin").trim();
                                        intent = new Intent(context, HalamanHomeAdminActivity.class);

                                    } else {
                                        sessionManager.logout();
                                    }

                                    sessionManager.setSessionLogin(id_user,nama,username);
                                    loginView.onLoginSuccess("Selamat Datang " + nama + " , " + id_user + " , " + username);
                                    context.startActivity(intent);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loginView.onLoginError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginView.onLoginError("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
