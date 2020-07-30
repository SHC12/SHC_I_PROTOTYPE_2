package com.mobile.spk.model;

public class JadwalPersonalDanruModel {
    private String no;
    private String nama;

    public JadwalPersonalDanruModel(String no, String nama) {
        this.no = no;
        this.nama = nama;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
