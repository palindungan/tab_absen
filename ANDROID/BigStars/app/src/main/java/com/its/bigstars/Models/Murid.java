package com.its.bigstars.Models;

public class Murid {
    String id_murid, id_wali_murid ,nama, nama_wali_murid, alamat, foto;
    String id_kelas_p , id_detail_kelas_p;

    public String getId_murid() {
        return id_murid;
    }

    public void setId_murid(String id_murid) {
        this.id_murid = id_murid;
    }

    public String getId_wali_murid() {
        return id_wali_murid;
    }

    public void setId_wali_murid(String id_wali_murid) {
        this.id_wali_murid = id_wali_murid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_wali_murid() {
        return nama_wali_murid;
    }

    public void setNama_wali_murid(String nama_wali_murid) {
        this.nama_wali_murid = nama_wali_murid;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId_kelas_p() {
        return id_kelas_p;
    }

    public void setId_kelas_p(String id_kelas_p) {
        this.id_kelas_p = id_kelas_p;
    }

    public String getId_detail_kelas_p() {
        return id_detail_kelas_p;
    }

    public void setId_detail_kelas_p(String id_detail_kelas_p) {
        this.id_detail_kelas_p = id_detail_kelas_p;
    }
}
