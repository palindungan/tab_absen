package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.view.IPengajarKelasTampilSemuaView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Kelas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengajarKelasTampilSemuaPresenter implements IPengajarKelasTampilSemuaPresenter {

    Context context;
    IPengajarKelasTampilSemuaView pengajarKelasTampilSemuaView;

    BaseUrl baseUrl;

    ArrayList<Kelas> dataModelArrayList;

    public PengajarKelasTampilSemuaPresenter(Context context, IPengajarKelasTampilSemuaView pengajarKelasTampilSemuaView) {
        this.context = context;
        this.pengajarKelasTampilSemuaView = pengajarKelasTampilSemuaView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_pengajar) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "admin/kelas_pertemuan/ambil_data_kelas_pertemuan"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            if (obj.optString("success").equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("list_kelas");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Kelas playerModel = new Kelas();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_kelas_p = dataobj.getString("id_kelas_p");
                                    String hari = dataobj.getString("hari");
                                    String jam_mulai = dataobj.getString("jam_mulai");
                                    String jam_berakhir = dataobj.getString("jam_berakhir");
                                    String harga_fee = dataobj.getString("harga_fee");
                                    String nama_pelajaran = dataobj.getString("nama_pelajaran");
                                    String nama_sharing = dataobj.getString("nama_sharing");

                                    String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                                    String id_pengajar = dataobj.getString("id_pengajar");
                                    String id_sharing = dataobj.getString("id_sharing");
                                    String nama_pengajar = dataobj.getString("nama_pengajar");

                                    playerModel.setId_kelas_p(id_kelas_p);
                                    playerModel.setHari(hari);
                                    playerModel.setJam_mulai(jam_mulai);
                                    playerModel.setJam_berakhir(jam_berakhir);
                                    playerModel.setHarga_fee(harga_fee);
                                    playerModel.setNama_pelajaran(nama_pelajaran);
                                    playerModel.setNama_sharing(nama_sharing);

                                    playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                                    playerModel.setId_pengajar(id_pengajar);
                                    playerModel.setId_sharing(id_sharing);
                                    playerModel.setNama_pengajar(nama_pengajar);

                                    dataModelArrayList.add(playerModel);
                                }

                                pengajarKelasTampilSemuaView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                pengajarKelasTampilSemuaView.onSetupListView(dataModelArrayList);
                                pengajarKelasTampilSemuaView.onErrorMessage("Database Kosong Silahkan Menambah Data Baru !");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pengajarKelasTampilSemuaView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pengajarKelasTampilSemuaView.onErrorMessage("Volley Error : " + error.toString());
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
