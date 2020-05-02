package com.its.bigstars.Controllers;

public class GlobalMessage {

    String messageConnectionError;
    String messageResponseError;

    public GlobalMessage() {
        messageConnectionError = "Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda";
        messageResponseError = "Kesalahan Menerima Data : ";
    }

    public String getMessageConnectionError() {
        return messageConnectionError;
    }

    public String getMessageResponseError() {
        return messageResponseError;
    }
}
