package com.mobile.spk.model;

public class AbsenHariIni {
    private String no;
    private String nama;
    private String sf;
    private String st;

    public AbsenHariIni(String no, String nama, String sf, String st) {
        this.no = no;
        this.nama = nama;
        this.sf = sf;
        this.st = st;
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

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
}
