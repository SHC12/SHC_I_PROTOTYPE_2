package com.mobile.spk.model;

public class Informasi {
    private String tanggal;
    private String judul;

    public Informasi(String tanggal, String judul) {
        this.tanggal = tanggal;
        this.judul = judul;
    }

    public Informasi() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}
