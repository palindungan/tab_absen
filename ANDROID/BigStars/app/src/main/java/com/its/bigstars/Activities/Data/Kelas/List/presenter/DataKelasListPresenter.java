package com.its.bigstars.Activities.Data.Kelas.List.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.Kelas.List.view.IDataKelasListView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Kelas;
import com.its.bigstars.Models.Pengajar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataKelasListPresenter implements IDataKelasListPresenter {

    Context context;
    IDataKelasListView dataKelasListView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    ArrayList<Kelas> dataModelArrayList;

    public DataKelasListPresenter(Context context, IDataKelasListView dataKelasListView) {
        this.context = context;
        this.dataKelasListView = dataKelasListView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onLoadDataList(String id) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/list_kelas"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String success = obj.getString("success");
                            String message = obj.getString("message");

                            if (success.equals("1")) {

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data_result");
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

                                    String jumlah_murid = dataobj.getString("jumlah_murid");

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

                                    playerModel.setJumlah_murid(jumlah_murid);

                                    dataModelArrayList.add(playerModel);
                                }

                                dataKelasListView.onSetupListView(dataModelArrayList);
                            } else {
                                dataModelArrayList = new ArrayList<>();
                                dataKelasListView.onSetupListView(dataModelArrayList);
                                toastMessage.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengajar", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDelete(String id) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/delete_kelas"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                toastMessage.onSuccessMessage(message);
                                dataKelasListView.onRefreshDataList();
                            } else {
                                toastMessage.onErrorMessage(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
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
