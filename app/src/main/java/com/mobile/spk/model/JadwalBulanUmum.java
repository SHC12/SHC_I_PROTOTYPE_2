package com.mobile.spk.model;

public class JadwalBulanUmum {
    private String tanggal;
    private String lokasi;


    public JadwalBulanUmum(String tanggal, String lokasi) {
        this.tanggal = tanggal;
        this.lokasi = lokasi;
    }

    public JadwalBulanUmum() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
