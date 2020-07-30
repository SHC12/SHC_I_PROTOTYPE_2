package com.mobile.spk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatPatroliModel implements Parcelable
{

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("kode_jadwal")
    @Expose
    private String kodeJadwal;
    @SerializedName("kode_non_format")
    @Expose
    private String kodeNonFormat;
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
    @SerializedName("patroli")
    @Expose
    private String patroli;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("lantai_1")
    @Expose
    private String lantai1;
    @SerializedName("lantai_2")
    @Expose
    private String lantai2;
    @SerializedName("lantai_3")
    @Expose
    private String lantai3;
    @SerializedName("lantai_4")
    @Expose
    private String lantai4;
    @SerializedName("lantai_5")
    @Expose
    private String lantai5;
    @SerializedName("lantai_6")
    @Expose
    private String lantai6;
    @SerializedName("lantai_7")
    @Expose
    private String lantai7;
    @SerializedName("lantai_8")
    @Expose
    private String lantai8;
    @SerializedName("lantai_basement")
    @Expose
    private String lantaiBasement;
    @SerializedName("file_1")
    @Expose
    private String file1;
    @SerializedName("file_2")
    @Expose
    private String file2;
    @SerializedName("file_3")
    @Expose
    private String file3;
    @SerializedName("file_4")
    @Expose
    private String file4;
    @SerializedName("file_5")
    @Expose
    private String file5;
    @SerializedName("file_6")
    @Expose
    private String file6;
    @SerializedName("file_7")
    @Expose
    private String file7;
    @SerializedName("file_8")
    @Expose
    private String file8;
    @SerializedName("file_basement")
    @Expose
    private String fileBasement;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    public final static Parcelable.Creator<RiwayatPatroliModel> CREATOR = new Creator<RiwayatPatroliModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RiwayatPatroliModel createFromParcel(Parcel in) {
            return new RiwayatPatroliModel(in);
        }

        public RiwayatPatroliModel[] newArray(int size) {
            return (new RiwayatPatroliModel[size]);
        }

    }
            ;

    protected RiwayatPatroliModel(Parcel in) {
        this.no = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.kodeJadwal = ((String) in.readValue((String.class.getClassLoader())));
        this.kodeNonFormat = ((String) in.readValue((String.class.getClassLoader())));
        this.namaPetugas = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggal = ((String) in.readValue((String.class.getClassLoader())));
        this.lokasi = ((String) in.readValue((String.class.getClassLoader())));
        this.shift = ((String) in.readValue((String.class.getClassLoader())));
        this.patroli = ((String) in.readValue((String.class.getClassLoader())));
        this.jam = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai1 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai2 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai3 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai4 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai5 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai6 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai7 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantai8 = ((String) in.readValue((String.class.getClassLoader())));
        this.lantaiBasement = ((String) in.readValue((String.class.getClassLoader())));
        this.file1 = ((String) in.readValue((String.class.getClassLoader())));
        this.file2 = ((String) in.readValue((String.class.getClassLoader())));
        this.file3 = ((String) in.readValue((String.class.getClassLoader())));
        this.file4 = ((String) in.readValue((String.class.getClassLoader())));
        this.file5 = ((String) in.readValue((String.class.getClassLoader())));
        this.file6 = ((String) in.readValue((String.class.getClassLoader())));
        this.file7 = ((String) in.readValue((String.class.getClassLoader())));
        this.file8 = ((String) in.readValue((String.class.getClassLoader())));
        this.fileBasement = ((String) in.readValue((String.class.getClassLoader())));
        this.keterangan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public RiwayatPatroliModel() {
    }

    /**
     *
     * @param no
     * @param keterangan
     * @param lantai2
     * @param patroli
     * @param lantai1
     * @param shift
     * @param kodeJadwal
     * @param fileBasement
     * @param jam
     * @param lantai6
     * @param lantai5
     * @param lantai4
     * @param lantai3
     * @param lantai8
     * @param lantai7
     * @param lantaiBasement
     * @param namaPetugas
     * @param file8
     * @param file6
     * @param file7
     * @param lokasi
     * @param file4
     * @param kodeNonFormat
     * @param file5
     * @param file2
     * @param file3
     * @param tanggal
     * @param file1
     */
    public RiwayatPatroliModel(Integer no, String kodeJadwal, String kodeNonFormat, String namaPetugas, String tanggal, String lokasi, String shift, String patroli, String jam, String lantai1, String lantai2, String lantai3, String lantai4, String lantai5, String lantai6, String lantai7, String lantai8, String lantaiBasement, String file1, String file2, String file3, String file4, String file5, String file6, String file7, String file8, String fileBasement, String keterangan) {
        super();
        this.no = no;
        this.kodeJadwal = kodeJadwal;
        this.kodeNonFormat = kodeNonFormat;
        this.namaPetugas = namaPetugas;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.shift = shift;
        this.patroli = patroli;
        this.jam = jam;
        this.lantai1 = lantai1;
        this.lantai2 = lantai2;
        this.lantai3 = lantai3;
        this.lantai4 = lantai4;
        this.lantai5 = lantai5;
        this.lantai6 = lantai6;
        this.lantai7 = lantai7;
        this.lantai8 = lantai8;
        this.lantaiBasement = lantaiBasement;
        this.file1 = file1;
        this.file2 = file2;
        this.file3 = file3;
        this.file4 = file4;
        this.file5 = file5;
        this.file6 = file6;
        this.file7 = file7;
        this.file8 = file8;
        this.fileBasement = fileBasement;
        this.keterangan = keterangan;
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

    public String getKodeNonFormat() {
        return kodeNonFormat;
    }

    public void setKodeNonFormat(String kodeNonFormat) {
        this.kodeNonFormat = kodeNonFormat;
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

    public String getPatroli() {
        return patroli;
    }

    public void setPatroli(String patroli) {
        this.patroli = patroli;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getLantai1() {
        return lantai1;
    }

    public void setLantai1(String lantai1) {
        this.lantai1 = lantai1;
    }

    public String getLantai2() {
        return lantai2;
    }

    public void setLantai2(String lantai2) {
        this.lantai2 = lantai2;
    }

    public String getLantai3() {
        return lantai3;
    }

    public void setLantai3(String lantai3) {
        this.lantai3 = lantai3;
    }

    public String getLantai4() {
        return lantai4;
    }

    public void setLantai4(String lantai4) {
        this.lantai4 = lantai4;
    }

    public String getLantai5() {
        return lantai5;
    }

    public void setLantai5(String lantai5) {
        this.lantai5 = lantai5;
    }

    public String getLantai6() {
        return lantai6;
    }

    public void setLantai6(String lantai6) {
        this.lantai6 = lantai6;
    }

    public String getLantai7() {
        return lantai7;
    }

    public void setLantai7(String lantai7) {
        this.lantai7 = lantai7;
    }

    public String getLantai8() {
        return lantai8;
    }

    public void setLantai8(String lantai8) {
        this.lantai8 = lantai8;
    }

    public String getLantaiBasement() {
        return lantaiBasement;
    }

    public void setLantaiBasement(String lantaiBasement) {
        this.lantaiBasement = lantaiBasement;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public String getFile4() {
        return file4;
    }

    public void setFile4(String file4) {
        this.file4 = file4;
    }

    public String getFile5() {
        return file5;
    }

    public void setFile5(String file5) {
        this.file5 = file5;
    }

    public String getFile6() {
        return file6;
    }

    public void setFile6(String file6) {
        this.file6 = file6;
    }

    public String getFile7() {
        return file7;
    }

    public void setFile7(String file7) {
        this.file7 = file7;
    }

    public String getFile8() {
        return file8;
    }

    public void setFile8(String file8) {
        this.file8 = file8;
    }

    public String getFileBasement() {
        return fileBasement;
    }

    public void setFileBasement(String fileBasement) {
        this.fileBasement = fileBasement;
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
        dest.writeValue(kodeNonFormat);
        dest.writeValue(namaPetugas);
        dest.writeValue(tanggal);
        dest.writeValue(lokasi);
        dest.writeValue(shift);
        dest.writeValue(patroli);
        dest.writeValue(jam);
        dest.writeValue(lantai1);
        dest.writeValue(lantai2);
        dest.writeValue(lantai3);
        dest.writeValue(lantai4);
        dest.writeValue(lantai5);
        dest.writeValue(lantai6);
        dest.writeValue(lantai7);
        dest.writeValue(lantai8);
        dest.writeValue(lantaiBasement);
        dest.writeValue(file1);
        dest.writeValue(file2);
        dest.writeValue(file3);
        dest.writeValue(file4);
        dest.writeValue(file5);
        dest.writeValue(file6);
        dest.writeValue(file7);
        dest.writeValue(file8);
        dest.writeValue(fileBasement);
        dest.writeValue(keterangan);
    }

    public int describeContents() {
        return 0;
    }

}
