package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter;

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
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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

                                    String alamat_foto = sessionManager.getUploadUrl() + "image/pengajar/" + foto + ".jpg";

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

    @Override
    public void onUpdatePengajar(String id_pengajar, String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp, String foto) {
        if (nama.isEmpty()) {
            formEditPengajarView.onErrorMessage("Nama Tidak Boleh Kosong !");
        } else if (username.isEmpty()) {
            formEditPengajarView.onErrorMessage("Username Tidak Boleh Kosong !");
        } else if (alamat.isEmpty()) {
            formEditPengajarView.onErrorMessage("Alamat Tidak Boleh Kosong !");
        } else if (no_hp.isEmpty()) {
            formEditPengajarView.onErrorMessage("No Hp Tidak Boleh Kosong !");
        } else {

            if (password.equals(konfirmasi_password)) {
                String URLstring = base_url + "pengajar/update_pengajar"; // url http request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        formEditPengajarView.onSucceessMessage("Berhasil Mengupdate Data");
                                        formEditPengajarView.backPressed();
                                    } else {
                                        formEditPengajarView.onErrorMessage("Gagal Mengupdate Data !");
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
                formEditPengajarView.onErrorMessage("Kesalahan Konfirmasi Password !");
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

    @Override
    public void hapusAkun(String id) {
        String URLstring = base_url + "pengajar/hapus_data_pengajar"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                formEditPengajarView.onSucceessMessage("Berhasil Menghapus Data");
                                formEditPengajarView.backPressed();
                            } else {
                                formEditPengajarView.onErrorMessage("Gagal Menghapus Data !");
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
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
