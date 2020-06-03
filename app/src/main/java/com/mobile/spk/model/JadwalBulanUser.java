package com.mobile.spk.model;

public class JadwalBulanUser {
    private String tanggal;
    private String shift;
    private String lokasi;

    public JadwalBulanUser(String tanggal, String shift, String lokasi) {
        this.tanggal = tanggal;
        this.shift = shift;
        this.lokasi = lokasi;
    }
    public JadwalBulanUser() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }


}
