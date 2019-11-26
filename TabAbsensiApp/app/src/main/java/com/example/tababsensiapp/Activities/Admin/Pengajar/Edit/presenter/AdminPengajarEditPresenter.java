package com.example.tababsensiapp.Activities.Admin.Pengajar.Edit.presenter;

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
import com.example.tababsensiapp.Activities.Admin.Pengajar.Edit.view.IAdminPengajarEditView;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminPengajarEditPresenter implements IAdminPengajarEditPresenter {

    Context context;
    IAdminPengajarEditView adminPengajarEditView;

    BaseUrl baseUrl;

    public AdminPengajarEditPresenter(Context context, IAdminPengajarEditView adminPengajarEditView) {
        this.context = context;
        this.adminPengajarEditView = adminPengajarEditView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/pengajar/ambil_data_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
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

                                    String alamat_foto = baseUrl.getUrlUpload() + "image/pengajar/" + foto + ".jpg";

                                    adminPengajarEditView.setNilaiDefault(nama, username, alamat, no_hp, alamat_foto);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminPengajarEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminPengajarEditView.onErrorMessage("Volley Error : " + error.toString());
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
    public void onUpdate(String id_pengajar, String nama, String username, String password, String alamat, String no_hp, String foto) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/pengajar/update_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminPengajarEditView.onSucceessMessage("Berhasil Mengupdate Data");
                                adminPengajarEditView.backPressed();
                            } else {
                                adminPengajarEditView.onErrorMessage("Gagal Mengupdate Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminPengajarEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminPengajarEditView.onErrorMessage("Volley Error : " + error.toString());
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

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/pengajar/delete_pengajar"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminPengajarEditView.onSucceessMessage("Berhasil Menghapus Data");
                                adminPengajarEditView.backPressed();
                            } else {
                                adminPengajarEditView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminPengajarEditView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminPengajarEditView.onErrorMessage("Volley Error : " + error.toString());
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
