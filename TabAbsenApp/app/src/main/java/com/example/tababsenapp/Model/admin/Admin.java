package com.example.tababsenapp.Model.admin;

public class Admin implements IAdmin{

    String id_admin, nama, username, password, foto;

    public Admin(String id_admin, String nama, String username, String password, String foto) {
        this.id_admin = id_admin;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.foto = foto;
    }

    @Override
    public String getIdAdmin() {
        return null;
    }

    @Override
    public String getNama() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getFoto() {
        return null;
    }
}
