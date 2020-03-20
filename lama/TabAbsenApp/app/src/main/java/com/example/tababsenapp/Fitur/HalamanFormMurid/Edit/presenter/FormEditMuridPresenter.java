package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.presenter;

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
import com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.view.IFormEditMuridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FormEditMuridPresenter implements IFormEditMuridPresenter {

    IFormEditMuridView formEditMuridView;
    Context context;

    SessionManager sessionManager;
    String base_url;

    public FormEditMuridPresenter(IFormEditMuridView formEditMuridView, Context context) {
        this.formEditMuridView = formEditMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_murid) {
        String URLstring = base_url + "murid/ambil_data_murid"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
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

                                    String alamat_foto = sessionManager.getUploadUrl() + "image/murid/" + foto + ".jpg";

                                    formEditMuridView.setNilaiDefault(nama,id_wali_murid, nama_wali_murid, alamat, alamat_foto);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditMuridView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditMuridView.onErrorMessage("Volley Error : " + error.toString());
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
    public void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto) {
        if (nama.isEmpty()) {
            formEditMuridView.onErrorMessage("Nama Tidak Boleh Kosong !");
        } else {

            String URLstring = base_url + "murid/update_murid"; // url http request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    formEditMuridView.onSucceessMessage("Berhasil Mengupdate Data");
                                    formEditMuridView.backPressed();
                                } else {
                                    formEditMuridView.onErrorMessage("Gagal Mengupdate Data !");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                formEditMuridView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            formEditMuridView.onErrorMessage("Volley Error : " + error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id_murid", id_murid);
                    params.put("id_wali_murid", id_wali_murid);
                    params.put("nama", nama);
                    params.put("foto", foto);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
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
        String URLstring = base_url + "murid/delete_murid"; // url http request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                formEditMuridView.onSucceessMessage("Berhasil Menghapus Data");
                                formEditMuridView.backPressed();
                            } else {
                                formEditMuridView.onErrorMessage("Gagal Menghapus Data !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            formEditMuridView.onErrorMessage("Kesalahan Menerima Data : " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        formEditMuridView.onErrorMessage("Volley Error : " + error.toString());
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
