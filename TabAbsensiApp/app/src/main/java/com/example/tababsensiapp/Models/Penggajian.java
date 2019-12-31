package com.example.tababsensiapp.Models;

public class Penggajian {
    String id_penggajian, id_pengajar, id_admin, waktu, total_pertemuan, total_harga_fee;

    public String getId_penggajian() {
        return id_penggajian;
    }

    public void setId_penggajian(String id_penggajian) {
        this.id_penggajian = id_penggajian;
    }

    public String getId_pengajar() {
        return id_pengajar;
    }

    public void setId_pengajar(String id_pengajar) {
        this.id_pengajar = id_pengajar;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTotal_pertemuan() {
        return total_pertemuan;
    }

    public void setTotal_pertemuan(String total_pertemuan) {
        this.total_pertemuan = total_pertemuan;
    }

    public String getTotal_harga_fee() {
        return total_harga_fee;
    }

    public void setTotal_harga_fee(String total_harga_fee) {
        this.total_harga_fee = total_harga_fee;
    }
}
