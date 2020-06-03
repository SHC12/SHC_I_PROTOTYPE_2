package com.mobile.spk.model;

public class Cuti {
    private String no;
    private String tanggal;

    private String status;

    public Cuti(String no, String tanggal, String status) {
        this.no = no;
        this.tanggal = tanggal;

        this.status = status;
    }

    public Cuti() {
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



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
