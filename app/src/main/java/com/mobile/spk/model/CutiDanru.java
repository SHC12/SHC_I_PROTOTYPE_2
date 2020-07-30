package com.mobile.spk.model;

public class CutiDanru {
    private String tanggal;
    private String nama;

    public CutiDanru(String tanggal, String nama) {
        this.tanggal = tanggal;
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
