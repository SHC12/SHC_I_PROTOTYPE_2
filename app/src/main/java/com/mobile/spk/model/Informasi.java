package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Informasi implements Parcelable {
    private String id_informasi;
    private String tanggal;
    private String nama_user;
    private String judul_informasi;
    private String detail_informasi;

    protected Informasi(Parcel in) {
        id_informasi = in.readString();
        tanggal = in.readString();
        nama_user = in.readString();
        judul_informasi = in.readString();
        detail_informasi = in.readString();
        file = in.readString();
    }

    public static final Creator<Informasi> CREATOR = new Creator<Informasi>() {
        @Override
        public Informasi createFromParcel(Parcel in) {
            return new Informasi(in);
        }

        @Override
        public Informasi[] newArray(int size) {
            return new Informasi[size];
        }
    };

    public String getId_informasi() {
        return id_informasi;
    }

    public void setId_informasi(String id_informasi) {
        this.id_informasi = id_informasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getJudul_informasi() {
        return judul_informasi;
    }

    public void setJudul_informasi(String judul_informasi) {
        this.judul_informasi = judul_informasi;
    }

    public String getDetail_informasi() {
        return detail_informasi;
    }

    public void setDetail_informasi(String detail_informasi) {
        this.detail_informasi = detail_informasi;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    private String file;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_informasi);
        dest.writeString(tanggal);
        dest.writeString(nama_user);
        dest.writeString(judul_informasi);
        dest.writeString(detail_informasi);
        dest.writeString(file);
    }
}
