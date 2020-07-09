package com.example.tababsensiapp.Controllers;

public class BaseUrl {
    String urlData;
    String urlUpload;

    String ipAddress = "http://bigstarsjember.online/";

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
