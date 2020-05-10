package com.its.bigstars.Activities.Data.Kelas.Edit.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.bigstars.Activities.Data.Kelas.Edit.view.IDataKelasEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalMessage;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.Models.Murid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataKelasEditPresenter implements IDataKelasEditPresenter {

    Context context;
    IDataKelasEditView dataKelasEditView;

    BaseUrl baseUrl;
    GlobalMessage globalMessage;
    ToastMessage toastMessage;

    ArrayList<MataPelajaran> dataModelArrayListPelajaran;
    ArrayList<Murid> dataModelArrayListMurid;

    public DataKelasEditPresenter(Context context, IDataKelasEditView dataKelasEditView) {
        this.context = context;
        this.dataKelasEditView = dataKelasEditView;

        baseUrl = new BaseUrl();
        globalMessage = new GlobalMessage();
        toastMessage = new ToastMessage(context);
    }

    @Override
    public void onUpdate(String id_kelas_p, String id_pengajar, String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/update_kelas"; // url http request

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
                                dataKelasEditView.backPressed();
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
                params.put("id_kelas_p", id_kelas_p);
                params.put("id_pengajar", id_pengajar);
                params.put("id_mata_pelajaran", id_mata_pelajaran);
                params.put("hari", hari);
                params.put("jam_mulai", jam_mulai);
                params.put("jam_berakhir", jam_berakhir);
                params.put("harga_fee", harga_fee);
                params.put("harga_spp", harga_spp);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadDataListPelajaran() {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/mata_pelajaran/list_mata_pelajaran"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);

                    String success = obj.getString("success");
                    String message = obj.getString("message");

                    if (success.equals("1")) {

                        dataModelArrayListPelajaran = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("data_result");
                        for (int i = 0; i < dataArray.length(); i++) {

                            MataPelajaran playerModel = new MataPelajaran();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
                            String nama = dataobj.getString("nama");

                            playerModel.setId_mata_pelajaran(id_mata_pelajaran);
                            playerModel.setNama(nama);

                            dataModelArrayListPelajaran.add(playerModel);
                        }
                        dataKelasEditView.onSetupListViewPelajaranDialog(dataModelArrayListPelajaran);

                    } else {
                        dataModelArrayListPelajaran = new ArrayList<>();
                        dataKelasEditView.onSetupListViewPelajaranDialog(dataModelArrayListPelajaran);
                        toastMessage.onErrorMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadDataListMurid(String id_kelas_p) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/list_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            String success = obj.getString("success");
                            String message = obj.getString("message");

                            if (success.equals("1")) {

                                dataModelArrayListMurid = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data_result");
                                for (int i = 0; i < dataArray.length(); i++) {

                                    Murid playerModel = new Murid();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id_detail_kelas_p = dataobj.getString("id_detail_kelas_p");
                                    String id_murid = dataobj.getString("id_murid");
                                    String nama = dataobj.getString("nama");
                                    String id_wali_murid = dataobj.getString("id_wali_murid");
                                    String nama_wali_murid = dataobj.getString("nama_wali_murid");
                                    String alamat = dataobj.getString("alamat");
                                    String foto = dataobj.getString("foto");

                                    playerModel.setId_detail_kelas_p(id_detail_kelas_p);
                                    playerModel.setId_murid(id_murid);
                                    playerModel.setNama(nama);
                                    playerModel.setId_wali_murid(id_wali_murid);
                                    playerModel.setNama_wali_murid(nama_wali_murid);
                                    playerModel.setAlamat(alamat);
                                    playerModel.setFoto(foto);

                                    dataModelArrayListMurid.add(playerModel);
                                }
                                dataKelasEditView.onSetupListViewMurid(dataModelArrayListMurid);

                            } else {
                                dataModelArrayListMurid = new ArrayList<>();
                                dataKelasEditView.onSetupListViewMurid(dataModelArrayListMurid);
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
                params.put("id_kelas_p", id_kelas_p);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onLoadDataListSemuaMurid() {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/murid/list_murid"; // url http request

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);

                    String success = obj.getString("success");
                    String message = obj.getString("message");

                    if (success.equals("1")) {

                        dataModelArrayListMurid = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("data_result");
                        for (int i = 0; i < dataArray.length(); i++) {

                            Murid playerModel = new Murid();
                            JSONObject dataobj = dataArray.getJSONObject(i);

                            String id_detail_kelas_p = "kosong";
                            String id_murid = dataobj.getString("id_murid");
                            String nama = dataobj.getString("nama");
                            String id_wali_murid = dataobj.getString("id_wali_murid");
                            String nama_wali_murid = dataobj.getString("nama_wali_murid");
                            String alamat = dataobj.getString("alamat");
                            String foto = dataobj.getString("foto");

                            playerModel.setId_detail_kelas_p(id_detail_kelas_p);
                            playerModel.setId_murid(id_murid);
                            playerModel.setNama(nama);
                            playerModel.setId_wali_murid(id_wali_murid);
                            playerModel.setNama_wali_murid(nama_wali_murid);
                            playerModel.setAlamat(alamat);
                            playerModel.setFoto(foto);

                            dataModelArrayListMurid.add(playerModel);
                        }
                        dataKelasEditView.onSetupListViewMuridDialog(dataModelArrayListMurid);

                    } else {
                        dataModelArrayListMurid = new ArrayList<>();
                        dataKelasEditView.onSetupListViewMuridDialog(dataModelArrayListMurid);
                        toastMessage.onErrorMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    toastMessage.onErrorMessage(globalMessage.getMessageResponseError() + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage.onErrorMessage(globalMessage.getMessageConnectionError());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onAddMurid(String id_kelas_p, String id_murid) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/add_murid"; // url http request

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
                                dataKelasEditView.onRefreshDataListMurid();
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
                params.put("id_kelas_p", id_kelas_p);
                params.put("id_murid", id_murid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDeleteMurid(String id_detail_kelas_p) {
        String base_url = baseUrl.getUrlData();
        String URL_DATA = base_url + "data/kelas_pertemuan/delete_murid"; // url http request

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
                                dataKelasEditView.onRefreshDataListMurid();
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
                params.put("id_detail_kelas_p", id_detail_kelas_p);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
