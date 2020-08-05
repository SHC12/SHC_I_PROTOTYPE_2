package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jadwal implements Parcelable
{

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("kode_jadwal")
    @Expose
    private String kodeJadwal;
    @SerializedName("nama_petugas")
    @Expose
    private String namaPetugas;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("status_absen")
    @Expose
    private String statusAbsen;
    @SerializedName("kode_absen")
    @Expose
    private String kodeAbsen;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    public final static Parcelable.Creator<Jadwal> CREATOR = new Creator<Jadwal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Jadwal createFromParcel(Parcel in) {
            return new Jadwal(in);
        }

        public Jadwal[] newArray(int size) {
            return (new Jadwal[size]);
        }

    }
            ;

    protected Jadwal(Parcel in) {
        this.no = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.kodeJadwal = ((String) in.readValue((String.class.getClassLoader())));
        this.namaPetugas = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggal = ((String) in.readValue((String.class.getClassLoader())));
        this.lokasi = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.statusAbsen = ((String) in.readValue((String.class.getClassLoader())));
        this.kodeAbsen = ((String) in.readValue((String.class.getClassLoader())));
        this.keterangan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Jadwal() {
    }

    /**
     *
     * @param no
     * @param keterangan
     * @param lokasi
     * @param shift
     * @param tanggal
     * @param statusAbsen
     * @param kodeAbsen
     * @param kodeJadwal
     * @param namaPetugas
     */
    public Jadwal(Integer no, String kodeJadwal, String namaPetugas, String tanggal, String lokasi, String shift, String statusAbsen, String kodeAbsen, String keterangan) {
        super();
        this.no = no;
        this.kodeJadwal = kodeJadwal;
        this.namaPetugas = namaPetugas;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.shift = shift;
        this.statusAbsen = statusAbsen;
        this.kodeAbsen = kodeAbsen;
        this.keterangan = keterangan;
    }
    public static Creator<Jadwal> getCREATOR() {
        return CREATOR;
    }
    public String getKodeAbsen() {
        return kodeAbsen;
    }

    public void setKodeAbsen(String kodeAbsen) {
        this.kodeAbsen = kodeAbsen;
    }



    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getKodeJadwal() {
        return kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStatusAbsen() {
        return statusAbsen;
    }

    public void setStatusAbsen(String statusAbsen) {
        this.statusAbsen = statusAbsen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(no);
        dest.writeValue(kodeJadwal);
        dest.writeValue(namaPetugas);
        dest.writeValue(tanggal);
        dest.writeValue(lokasi);
        dest.writeValue(shift);
        dest.writeValue(statusAbsen);
        dest.writeValue(kodeAbsen);
        dest.writeValue(keterangan);
    }

    public int describeContents() {
        return 0;
    }

}