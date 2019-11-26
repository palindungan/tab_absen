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

import org.json.JSONArray;
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
    public void inisiasiAwal(String id_admin) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/akun_saya/ambil_data_admin"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("admin");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String username = object.getString("username").trim();
                                    String foto = object.getString("foto").trim();

                                    String alamat_foto = baseUrl.getUrlUpload() + "image/admin/" + foto + ".jpg";

                                    adminAkunSayaView.setNilaiDefault(nama, username, alamat_foto);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminAkunSayaView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminAkunSayaView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_admin", id_admin);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/akun_saya/update_admin"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminAkunSayaView.onSucceessMessage("Berhasil Mengupdate Data");
                                adminAkunSayaView.backPressed();
                            } else {
                                adminAkunSayaView.onErrorMessage("Gagal Mengupdate Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminAkunSayaView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminAkunSayaView.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_admin", id_admin);
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
