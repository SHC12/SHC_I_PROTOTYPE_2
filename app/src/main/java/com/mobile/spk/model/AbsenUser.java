package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AbsenUser implements Parcelable {
    @SerializedName("kode_jadwal")
    private String kode;
    @SerializedName("nama_petugas")
    private String namaPetugas;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("status_absen")
    private String status_absen;
    @SerializedName("shift")
    private String status_shift;
    @SerializedName("keterangan")
    private String keterangan;

    protected AbsenUser(Parcel in) {
        kode = in.readString();
        namaPetugas = in.readString();
        tanggal = in.readString();
        lokasi = in.readString();
        status_absen = in.readString();
        status_shift = in.readString();
        keterangan = in.readString();
    }

    public static final Creator<AbsenUser> CREATOR = new Creator<AbsenUser>() {
        @Override
        public AbsenUser createFromParcel(Parcel in) {
            return new AbsenUser(in);
        }

        @Override
        public AbsenUser[] newArray(int size) {
            return new AbsenUser[size];
        }
    };

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getStatus_absen() {
        return status_absen;
    }

    public void setStatus_absen(String status_absen) {
        this.status_absen = status_absen;
    }

    public String getStatus_shift() {
        return status_shift;
    }

    public void setStatus_shift(String status_shift) {
        this.status_shift = status_shift;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kode);
        dest.writeString(namaPetugas);
        dest.writeString(tanggal);
        dest.writeString(lokasi);
        dest.writeString(status_absen);
        dest.writeString(status_shift);
        dest.writeString(keterangan);
    }
}
