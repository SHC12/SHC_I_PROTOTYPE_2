package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataAnggota implements Parcelable {
    public static final Creator<DataAnggota> CREATOR = new Creator<DataAnggota>() {
        @Override
        public DataAnggota createFromParcel(Parcel in) {
            return new DataAnggota(in);
        }

        @Override
        public DataAnggota[] newArray(int size) {
            return new DataAnggota[size];
        }
    };
    @SerializedName("no")
    private String no;
    @SerializedName("id_anggota")
    private String id;
    @SerializedName("tanggal_registrasi")
    private String tgl;
    @SerializedName("nomor_anggota")
    private String noAngota;
    @SerializedName("nama_lengkap")
    private String nama;
    @SerializedName("nama_jabatan")
    private String jabatan;
    @SerializedName("nama_mitra")
    private String mitra;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("status")
    private String status;
    protected DataAnggota(Parcel in) {
        no = in.readString();
        id = in.readString();
        tgl = in.readString();
        noAngota = in.readString();
        nama = in.readString();
        jabatan = in.readString();
        mitra = in.readString();
        noHp = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
        status = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getNoAngota() {
        return noAngota;
    }

    public void setNoAngota(String noAngota) {
        this.noAngota = noAngota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getMitra() {
        return mitra;
    }

    public void setMitra(String mitra) {
        this.mitra = mitra;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no);
        dest.writeString(id);
        dest.writeString(tgl);
        dest.writeString(noAngota);
        dest.writeString(nama);
        dest.writeString(jabatan);
        dest.writeString(mitra);
        dest.writeString(noHp);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(status);
    }
}
