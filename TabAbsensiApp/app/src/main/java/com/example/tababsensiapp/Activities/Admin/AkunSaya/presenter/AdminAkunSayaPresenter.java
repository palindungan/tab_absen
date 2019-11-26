package com.example.tababsensiapp.Activities.Admin.AkunSaya.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Admin.AkunSaya.view.IAdminAkunSayaView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminAkunSayaPresenter implements IAdminAkunSayaPresenter {

    Context context;
    IAdminAkunSayaView adminAkunSayaView;

    BaseUrl baseUrl;

    public AdminAkunSayaPresenter(Context context, IAdminAkunSayaView adminAkunSayaView) {
        this.context = context;
        this.adminAkunSayaView = adminAkunSayaView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onSubmit(String nama, String username, String password, String foto) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "pengajar/tambah_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminAkunSayaView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                adminAkunSayaView.backPressed();
                            } else {
                                adminAkunSayaView.onSubmitError("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminAkunSayaView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminAkunSayaView.onSubmitError("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
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
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
}
