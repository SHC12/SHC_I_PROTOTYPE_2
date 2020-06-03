package com.mobile.spk.model;

public class JadwalDetailUmum {
    private String shift;
    private String nama_petugas;

    public JadwalDetailUmum(String shift, String nama_petugas) {
        this.shift = shift;
        this.nama_petugas = nama_petugas;
    }

    public JadwalDetailUmum() {
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }
}
