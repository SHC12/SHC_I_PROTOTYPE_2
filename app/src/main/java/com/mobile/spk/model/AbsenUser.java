package com.mobile.spk.model;

public class AbsenUser {
    private String no;
    private String tanggal;
    private String jam;
    private String status;

    public AbsenUser(String no, String tanggal, String jam, String status) {
        this.no = no;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
    }

    public AbsenUser() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
