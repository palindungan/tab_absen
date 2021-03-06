package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter;

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
import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.view.IFormTambahPengajarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FormTambahPengajarPresenter implements IFormTambahPengajarPresenter {

    IFormTambahPengajarView formTambahPengajarView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormTambahPengajarPresenter(IFormTambahPengajarView formTambahPengajarView, Context context) {
        this.formTambahPengajarView = formTambahPengajarView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onSubmitPengajar(String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp, String foto) {

        if (nama.isEmpty()) {
            formTambahPengajarView.onSubmitError("Nama Tidak Boleh Kosong !");
        } else if (username.isEmpty()) {
            formTambahPengajarView.onSubmitError("Username Tidak Boleh Kosong !");
        } else if (password.isEmpty()) {
            formTambahPengajarView.onSubmitError("Passowrd Tidak Boleh Kosong !");
        } else if (konfirmasi_password.isEmpty()) {
            formTambahPengajarView.onSubmitError("Konfirmasi Password Tidak Boleh Kosong !");
        } else if (alamat.isEmpty()) {
            formTambahPengajarView.onSubmitError("Alamat Tidak Boleh Kosong !");
        } else if (no_hp.isEmpty()) {
            formTambahPengajarView.onSubmitError("No Hp Tidak Boleh Kosong !");
        } else {

            if (password.equals(konfirmasi_password)) {
                String URLstring = base_url + "pengajar/tambah_pengajar"; // url http request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        formTambahPengajarView.onSubmitSuccess("Berhasil Menambah Data Pengajar Baru");
                                        formTambahPengajarView.backPressed();
                                    } else {
                                        formTambahPengajarView.onSubmitError("Gagal Menambah Data");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    formTambahPengajarView.onSubmitError("Kesalahan Menerima Data : " + e.toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                formTambahPengajarView.onSubmitError("Volley Error : " + error.toString());
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
            } else {
                formTambahPengajarView.onSubmitError("Kesalahan Konfirmasi Password !");
            }
        }
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
