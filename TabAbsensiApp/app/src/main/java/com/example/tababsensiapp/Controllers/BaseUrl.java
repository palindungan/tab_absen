package com.example.tababsensiapp.Controllers;

public class BaseUrl {
    String urlData;
    String urlUpload;

    String ipAddress = "http://192.168.137.1/";

    public BaseUrl() {
        urlData = ipAddress + "tab_absen/web/api/";
        urlUpload = ipAddress + "tab_absen/web/upload/";
    }

    public String getUrlData() {
        return urlData;
    }

    public String getUrlUpload() {
        return urlUpload;
    }
}
