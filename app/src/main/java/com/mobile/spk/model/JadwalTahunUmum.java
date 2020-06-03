package com.mobile.spk.model;

public class JadwalTahunUmum {
    private String bulan;
    private String tahun;

    public JadwalTahunUmum(String bulan, String tahun) {
        this.bulan = bulan;
        this.tahun = tahun;
    }

    public JadwalTahunUmum() {
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}
