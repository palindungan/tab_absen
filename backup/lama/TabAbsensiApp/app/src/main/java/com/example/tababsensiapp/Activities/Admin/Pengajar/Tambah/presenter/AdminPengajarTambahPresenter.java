package com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.presenter;

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
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.view.IAdminPengajarTambahView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminPengajarTambahPresenter implements IAdminPengajarTambahPresenter {

    Context context;
    IAdminPengajarTambahView adminPengajarTambahView;

    BaseUrl baseUrl;

    public AdminPengajarTambahPresenter(Context context, IAdminPengajarTambahView adminPengajarTambahView) {
        this.context = context;
        this.adminPengajarTambahView = adminPengajarTambahView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void onSubmit(String nama, String username, String password, String alamat, String no_hp, String foto) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/pengajar/tambah_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminPengajarTambahView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                adminPengajarTambahView.backPressed();
                            } else {
                                adminPengajarTambahView.onSubmitError("Gagal Menambah Data");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminPengajarTambahView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminPengajarTambahView.onSubmitError("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
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
