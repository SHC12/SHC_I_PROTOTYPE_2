package com.mobile.spk.api;


import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.DataAnggota;
import com.mobile.spk.model.Informasi;
import com.mobile.spk.model.Jadwal;
import com.mobile.spk.model.Mitra;
import com.mobile.spk.model.PatroliModel;
import com.mobile.spk.model.RiwayatPatroliModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("api_android/get_jabatan.php")
    Call<ResponseBody> getJabatan();

    @GET("api_android/get_list_petugas.php")
    Call<ResponseBody> getPetugas();

    @GET("api_android/get_mitra.php")
    Call<ResponseBody> getMitra();

    @GET("api_android/get_kode_jadwal.php")
    Call<ResponseBody> getKodeJadwal();

    @GET("api_android/get_info_apps.php")
    Call<ResponseBody> getInfoApps();

    @GET("api_android/export_data_anggota.php")
    Call<ResponseBody> downloadDataAnggota();

    @GET("api_android/export_jadwal_by_gedung.php")
    Call<ResponseBody> downloadDataJadwalGedung(@Query("lokasi") String lokasi);

    @GET("api_android/export_jadwal_by_petugas.php")
    Call<ResponseBody> downloadDataJadwalPetugas(@Query("id_user") String idUser);

    @GET("api_android/export_riwayat_absen_petugas.php")
    Call<ResponseBody> downloadRiwayatAbsenPetugas(@Query("id_user") String idUser);

    @GET("api_android/export_riwayat_patroli_petugas.php")
    Call<ResponseBody> downloadRiwayatPatroliPetugas(@Query("id_user") String idUser);

    @GET("api_android/export_jadwal_personal_petugas.php")
    Call<ResponseBody> downloadJadwalPersonal(@Query("id_user") String idUser);

    @GET("api_android/export_data_mitra.php")
    Call<ResponseBody> downloadDataMitra();

    @FormUrlEncoded
    @POST("api_android/registrasi.php")
    Call<ResponseBody> registras(
            @Field("tgl_registrasi") String tgl,
            @Field("nomor_anggota") String nomorAnggota,
            @Field("nama_lengkap") String namaa,
            @Field("jabatan") String jabatan,
            @Field("mitra") String mitra,
            @Field("no_hp") String noHp,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api_android/update_user.php")
    Call<ResponseBody> updateUser(
            @Field("id_user") String id,
            @Field("nomor_anggota") String nomorAnggota,
            @Field("nama_lengkap") String namaa,
            @Field("jabatan") String jabatan,
            @Field("mitra") String mitra,
            @Field("no_hp") String noHp,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("api_android/pengajuan_cuti.php")
    Call<ResponseBody> pengajuanCuti(
            @Field("id_user") String id,
            @Field("tgl") String tgl,
            @Field("lama_cuti") String lama,
            @Field("alasan_cuti") String alasan,
            @Field("mulai_cuti") String mulai,
            @Field("selesai_cuti") String selesai,
            @Field("kembali_kerja") String kembali,
            @Field("no_kontrak") String kontrak,
            @Field("ket_tambahan") String ket
    );

    @FormUrlEncoded
    @POST("api_android/model_login.php")
    Call<ResponseBody> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api_android/get_data_cuti_user.php")
    Call<ResponseBody> dataCuti(
            @Field("id_user") String id
    );

    @FormUrlEncoded
    @POST("api_android/tambah_mitra.php")
    Call<ResponseBody> tambahMitra(
            @Field("nama_mitra") String nama
    );

    @FormUrlEncoded
    @POST("api_android/get_cuti.php")
    Call<List<Cuti>> riwayatCuti(
            @Field("id_user") String id
    );

    @FormUrlEncoded
    @POST("api_android/hapus_user.php")
    Call<ResponseBody> hapus_user(
            @Field("id_user") String id
    );

    @GET("api_android/get_anggota.php")
    Call<List<DataAnggota>> dataAnggota();

    @GET("api_android/get_list_mitra.php")
    Call<List<Mitra>> dataMitra();

    @GET("api_android/get_informasi.php")
    Call<List<Informasi>> dataInformasi();



    @GET("api_android/get_absensi_petugas.php")
    Call<List<AbsenUser>> jadwalUser(@Query("id_user") String idUser);

    @GET("api_android/get_riwayat_absen_petugas.php")
    Call<List<AbsenUser>> riwayatAbsen(@Query("id_user") String idUser);

//
//    @GET("api_android/get_riwayat_absen_petugas.php")
//    Call<List<AbsenUser>> riwayatAbsen(@Query("id_user") String idUser);

    @GET
    Call<ResponseBody> downloadFile(@Url String url);

    @Multipart
    @POST("api_android/penambahan_informasi.php")
    Call<ResponseBody> submitInformasi(
            @Part("tanggal") RequestBody tanggal,
            @Part("nama_user") RequestBody nama_user,
            @Part("judul_informasi") RequestBody judul,
            @Part("detail_informasi") RequestBody detail,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("api_android/penambahan_informasi.php")
    Call<ResponseBody> submitInformasinoFile(
            @Field("tanggal") String tanggal,
            @Field("nama_user") String nama_user,
            @Field("judul_informasi") String judul,
            @Field("detail_informasi") String detail);

    @FormUrlEncoded
    @POST("api_android/update_status_cuti.php")
    Call<ResponseBody> updateStatusCuti(
            @Field("id_cuti") String idCuti,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("api_android/post_absen_petugas.php")
    Call<ResponseBody> submitAbsen(
            @Field("kode_jadwal") String kodeJadwal,
            @Field("absen") int absen,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST("api_android/post_jadwal.php")
    Call<ResponseBody> inputJadwal(
            @Field("id_user") String kodeJadwal,
            @Field("tgl") String tgl,
            @Field("lokasi") String lokasi,
            @Field("shift") String shift
    );

    @FormUrlEncoded
    @POST("api_android/update_jadwal_by_gedung.php")
    Call<ResponseBody> updateJadwal(
            @Field("id_user") String idUser,
            @Field("tgl") String tgl,
            @Field("lokasi") String lokasi,
            @Field("shift") String shift,
            @Field("kode_jadwal") String kode
    );

    @FormUrlEncoded
    @POST("api_android/hapus_jadwal.php")
    Call<ResponseBody> hapusJadwal(
            @Field("kode_jadwal") String kode
    );

    @FormUrlEncoded
    @POST("api_android/hapus_mitra.php")
    Call<ResponseBody> hapusMitra(
            @Field("id_mitra") String kode
    );


    @GET("api_android/get_jadwal_by_gedung.php")
    Call<List<Jadwal>> getJadwalByGedung(@Query("lokasi") String lokasi);

    @GET("api_android/get_jadwal_by_petugas.php")
    Call<List<Jadwal>> getJadwalByPetugas(@Query("id_user") String id_user);

    @GET("api_android/get_jadwal_personal.php")
    Call<List<Jadwal>> getJadwalPersonal(@Query("id_user") String id_user);

    @GET("api_android/get_patroli_petugas.php")
    Call<List<PatroliModel>> getPatroliPetugas(@Query("id_user") String id_user);

    @GET("api_android/get_riwayat_patroli_petugas.php")
    Call<List<RiwayatPatroliModel>> getRiwayatPatroliPetugas(@Query("id_user") String id_user);

    @Multipart
    @POST("api_android/input_patroli.php")
    Call<ResponseBody> postPatroliGedungC(
            @Part("kode_jadwal") RequestBody kode_jadwal,
            @Part("id_user") RequestBody id_user,
            @Part("kode") RequestBody kode,
            @Part("jam") RequestBody jam,
            @PartMap Map<String, RequestBody> keterangan,
            @Part("lt1") RequestBody lt1,
            @Part("lt2") RequestBody lt2,
            @Part("lt3") RequestBody lt3,
            @Part("lt4") RequestBody lt4,
            @Part("lt5") RequestBody lt5,
            @Part("lt6") RequestBody lt6,
            @Part("lt7") RequestBody lt7,
            @Part("lt8") RequestBody lt8,
            @Part("basement") RequestBody lt_basement,
            @Part List<MultipartBody.Part> lt_file
        );







}
