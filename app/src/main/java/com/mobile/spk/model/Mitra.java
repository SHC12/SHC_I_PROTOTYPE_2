package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mitra implements Parcelable {
    @SerializedName("no")
    private String no;
    @SerializedName("id_mitra")
    private String id_mitra;
    @SerializedName("nama_mitra")
    private String nama;

    protected Mitra(Parcel in) {
        no = in.readString();
        id_mitra = in.readString();
        nama = in.readString();
    }

    public static final Creator<Mitra> CREATOR = new Creator<Mitra>() {
        @Override
        public Mitra createFromParcel(Parcel in) {
            return new Mitra(in);
        }

        @Override
        public Mitra[] newArray(int size) {
            return new Mitra[size];
        }
    };

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId_mitra() {
        return id_mitra;
    }

    public void setId_mitra(String id_mitra) {
        this.id_mitra = id_mitra;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no);
        dest.writeString(id_mitra);
        dest.writeString(nama);
    }
}
