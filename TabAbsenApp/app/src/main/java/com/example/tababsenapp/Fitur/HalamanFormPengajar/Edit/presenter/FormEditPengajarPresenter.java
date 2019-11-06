package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormEditPengajarPresenter implements IFormEditPengajarPresenter {

    IFormEditPengajarView formEditPengajarView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormEditPengajarPresenter(IFormEditPengajarView formEditPengajarView, Context context) {
        this.formEditPengajarView = formEditPengajarView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {

        String URLstring = base_url + "pengajar/ambil_data_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("pengajar");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();
                                    String alamat = object.getString("alamat").trim();
                                    String no_hp = object.getString("no_hp").trim();
                                    String foto = object.getString("foto").trim();

                                    String alamat_foto = sessionManager.getUploadUrl() + "image/pengajar/" + foto;

                                    formEditPengajarView.setNilaiDefault(nama, username, alamat, no_hp, alamat_foto);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditPengajarView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditPengajarView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id_pengajar);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
