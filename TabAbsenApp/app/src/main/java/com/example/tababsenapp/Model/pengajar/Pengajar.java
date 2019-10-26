package com.example.tababsenapp.Model.pengajar;

public class Pengajar implements IPengajar {

    String id_pengajar, nama, username, alamat, no_hp, foto;

    public Pengajar(String id_pengajar, String nama, String username, String alamat, String no_hp, String foto) {
        this.id_pengajar = id_pengajar;
        this.nama = nama;
        this.username = username;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.foto = foto;
    }

    @Override
    public String getIdPengajar() {
        return id_pengajar;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getAlamat() {
        return alamat;
    }

    @Override
    public String getNoHP() {
        return no_hp;
    }

    @Override
    public String getFoto() {
        return foto;
    }
}
