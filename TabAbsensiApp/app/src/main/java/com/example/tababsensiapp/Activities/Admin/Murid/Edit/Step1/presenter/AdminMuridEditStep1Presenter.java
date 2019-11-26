package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.presenter;

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
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.view.IAdminMuridEditStep1View;
import com.example.tababsensiapp.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AdminMuridEditStep1Presenter implements IAdminMuridEditStep1Presenter {

    Context context;
    IAdminMuridEditStep1View adminMuridEditStep1View;

    BaseUrl baseUrl;

    public AdminMuridEditStep1Presenter(Context context, IAdminMuridEditStep1View adminMuridEditStep1View) {
        this.context = context;
        this.adminMuridEditStep1View = adminMuridEditStep1View;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_murid) {

        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/murid/ambil_data_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("murid");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String id_wali_murid = object.getString("id_wali_murid").trim();
                                    String nama_wali_murid = object.getString("nama_wali_murid").trim();
                                    String alamat = object.getString("alamat").trim();
                                    String foto = object.getString("foto").trim();

                                    String alamat_foto = baseUrl.getUrlUpload() + "image/murid/" + foto + ".jpg";

                                    adminMuridEditStep1View.setNilaiDefault(nama,id_wali_murid, nama_wali_murid, alamat, alamat_foto);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMuridEditStep1View.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMuridEditStep1View.onErrorMessage("Volley Error : " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_murid", id_murid);
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
        String URL_DATA = base_url + "admin/murid/delete_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                adminMuridEditStep1View.onSuccessMessage("Berhasil Menghapus Data");
                                adminMuridEditStep1View.backPressed();
                            } else {
                                adminMuridEditStep1View.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adminMuridEditStep1View.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adminMuridEditStep1View.onErrorMessage("Volley Error : " + error.toString());
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
