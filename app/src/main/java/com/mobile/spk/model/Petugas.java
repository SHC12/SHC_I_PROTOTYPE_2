package com.mobile.spk.model;

public class Petugas {
    String id_user;
    String nama_lengkap;

    public Petugas() {
    }

    public Petugas(String id_user, String nama_lengkap) {
        this.id_user = id_user;
        this.nama_lengkap = nama_lengkap;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }
}
