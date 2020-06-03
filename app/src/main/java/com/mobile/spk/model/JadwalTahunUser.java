package com.mobile.spk.model;

public class JadwalTahunUser {
    private String bulan;
    private String tahun;

    public JadwalTahunUser(String bulan, String tahun) {
        this.bulan = bulan;
        this.tahun = tahun;
    }

    public JadwalTahunUser() {
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
