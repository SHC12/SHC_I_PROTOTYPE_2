package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cuti implements Parcelable {
    public static final Creator<Cuti> CREATOR = new Creator<Cuti>() {
        @Override
        public Cuti createFromParcel(Parcel in) {
            return new Cuti(in);
        }

        @Override
        public Cuti[] newArray(int size) {
            return new Cuti[size];
        }
    };

    @SerializedName("no")
    private String no;
    @SerializedName("id_cuti")
    private String id_cuti;
    @SerializedName("id_cuti_ori")
    private String id_cuti_ori;

    public String getId_cuti_ori() {
        return id_cuti_ori;
    }

    public void setId_cuti_ori(String id_cuti_ori) {
        this.id_cuti_ori = id_cuti_ori;
    }

    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("nama_lengkap")
    private String nama_lengkap;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("lokasi_kerja")
    private String lokasi_kerja;
    @SerializedName("lama_cuti")
    private String lama_cuti;
    @SerializedName("alasan_cuti")
    private String alasan_cuti;
    @SerializedName("mulai_cuti")
    private String mulai_cuti;
    @SerializedName("selesai_cuti")
    private String selesai_cuti;
    @SerializedName("kembali_kerja")
    private String kembali_kerja;
    @SerializedName("no_kontrak")
    private String kontak;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("status")
    private String status;

    protected Cuti(Parcel in) {
        no = in.readString();
        id_cuti = in.readString();
        id_cuti_ori = in.readString();
        tanggal = in.readString();
        nama_lengkap = in.readString();
        jabatan = in.readString();
        lokasi_kerja = in.readString();
        lama_cuti = in.readString();
        alasan_cuti = in.readString();
        kontak = in.readString();
        mulai_cuti = in.readString();
        selesai_cuti = in.readString();
        kembali_kerja = in.readString();
        keterangan = in.readString();
        status = in.readString();
    }

    public Cuti(String no, String tanggal, String status) {
        this.no = no;
        this.tanggal = tanggal;

        this.status = status;
    }

    public Cuti() {
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getId_cuti() {
        return id_cuti;
    }

    public void setId_cuti(String id_cuti) {
        this.id_cuti = id_cuti;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLokasi_kerja() {
        return lokasi_kerja;
    }

    public void setLokasi_kerja(String lokasi_kerja) {
        this.lokasi_kerja = lokasi_kerja;
    }

    public String getLama_cuti() {
        return lama_cuti;
    }

    public void setLama_cuti(String lama_cuti) {
        this.lama_cuti = lama_cuti;
    }

    public String getAlasan_cuti() {
        return alasan_cuti;
    }

    public void setAlasan_cuti(String alasan_cuti) {
        this.alasan_cuti = alasan_cuti;
    }

    public String getMulai_cuti() {
        return mulai_cuti;
    }

    public void setMulai_cuti(String mulai_cuti) {
        this.mulai_cuti = mulai_cuti;
    }

    public String getSelesai_cuti() {
        return selesai_cuti;
    }

    public void setSelesai_cuti(String selesai_cuti) {
        this.selesai_cuti = selesai_cuti;
    }

    public String getKembali_kerja() {
        return kembali_kerja;
    }

    public void setKembali_kerja(String kembali_kerja) {
        this.kembali_kerja = kembali_kerja;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(no);
        dest.writeString(id_cuti);
        dest.writeString(id_cuti_ori);
        dest.writeString(tanggal);
        dest.writeString(nama_lengkap);
        dest.writeString(jabatan);
        dest.writeString(lokasi_kerja);
        dest.writeString(lama_cuti);
        dest.writeString(alasan_cuti);
        dest.writeString(mulai_cuti);
        dest.writeString(kontak);
        dest.writeString(selesai_cuti);
        dest.writeString(kembali_kerja);
        dest.writeString(keterangan);
        dest.writeString(status);
    }
}
