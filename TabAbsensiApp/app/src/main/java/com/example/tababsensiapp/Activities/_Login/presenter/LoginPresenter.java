package com.example.tababsensiapp.Activities._Login.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.Home.AdminHomeActivity;
import com.example.tababsensiapp.Activities._Login.view.ILoginView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILoginPresenter {

    Context context;
    ILoginView loginView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }

    @Override
    public void onLogin(String username, String password, String hakAkses) {

        String base_url = baseUrl.getUrlData();
        String URL_LOGIN = base_url + "login/" + hakAkses; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id_user = "";
                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();

                                    Intent intent = new Intent();

                                    if (hakAkses.equals("admin")) {

                                        id_user = object.getString("id_admin").trim();
                                        intent = new Intent(context, AdminHomeActivity.class);

                                    } else if (hakAkses.equals("pengajar")) {

                                        loginView.onSuccessMessage("Pengajar");

                                    } else if (hakAkses.equals("wali_murid")) {

                                        loginView.onSuccessMessage("Wali Murid");

                                    } else {

                                        sessionManager.logout();
                                        
                                    }

                                    sessionManager.setSessionLogin(id_user, nama, username);
                                    loginView.onSuccessMessage("Selamat Datang " + nama + " , " + id_user + " , " + username);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    context.startActivity(intent);
                                }
                            } else if (success.equals("-1")) {
                                loginView.onErrorMessage(message);
                            } else if (success.equals("0")) {
                                loginView.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loginView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginView.onErrorMessage("Volley Error : " + error.toString());
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
