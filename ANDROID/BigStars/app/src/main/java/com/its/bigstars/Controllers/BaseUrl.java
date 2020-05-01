package com.its.bigstars.Controllers;

public class BaseUrl {
    String urlData;
    String urlUpload;

    String ipAddress = "http://192.168.137.1/tab_absen/";

    public BaseUrl() {
        urlData = ipAddress + "web/api/";
        urlUpload = ipAddress + "web/upload/";
    }

    public String getUrlData() {
        return urlData;
    }

    public String getUrlUpload() {
        return urlUpload;
    }
}
