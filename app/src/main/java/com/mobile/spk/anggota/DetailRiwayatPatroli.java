package com.mobile.spk.anggota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.RiwayatPatroliModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRiwayatPatroli extends AppCompatActivity {

    public static final String DETAIL_PATROLI = "detail_patroli";
    TextView kode_jadwal, nama, tanggal, lokasi, shift, jam, l1, l2, l3, l4, l5, l6, l7, l8, l9, f1, f2, f3, f4, f5, f6, f7, f8, f9, ket;
    TableRow r4, r5, r6, r7, r8, r9, s4, s5, s6, s7, s8, s9;

    String nama_gedung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_patroli);
        RiwayatPatroliModel detail = getIntent().getParcelableExtra(DETAIL_PATROLI);

        nama_gedung = detail.getLokasi();


        kode_jadwal = findViewById(R.id.tx_kode_jadwal_rp);
        tanggal = findViewById(R.id.tanggalDetailJadwalRP);
        nama = findViewById(R.id.namaDetailJadwalRP);
        lokasi = findViewById(R.id.lokasiJadwalRP);
        shift = findViewById(R.id.shiftJadwalRP);
        jam = findViewById(R.id.jamJadwalRP);
        l1 = findViewById(R.id.lt1RP);
        l2 = findViewById(R.id.lt2RP);
        l3 = findViewById(R.id.lt3RP);
        l4 = findViewById(R.id.lt4RP);
        l5 = findViewById(R.id.lt5RP);
        l6 = findViewById(R.id.lt6RP);
        l7 = findViewById(R.id.lt7RP);
        l8 = findViewById(R.id.lt8RP);
        l9 = findViewById(R.id.lt9RP);
        f1 = findViewById(R.id.lt1FileRP);
        f2 = findViewById(R.id.lt2FileRP);
        f3 = findViewById(R.id.lt3FileRP);
        f4 = findViewById(R.id.lt4FileRP);
        f5 = findViewById(R.id.lt5FileRP);
        f6 = findViewById(R.id.lt6FileRP);
        f7 = findViewById(R.id.lt7FileRP);
        f8 = findViewById(R.id.lt8FileRP);
        f9 = findViewById(R.id.lt9FileRP);
        ket = findViewById(R.id.keteranganJadwalRP);

        r4 = findViewById(R.id.R4RP);
        r5 = findViewById(R.id.R5RP);
        r6 = findViewById(R.id.R6RP);
        r7 = findViewById(R.id.R7RP);
        r8 = findViewById(R.id.R8RP);
        r9 = findViewById(R.id.R9RP);
        s4 = findViewById(R.id.S4RP);
        s5 = findViewById(R.id.S5RP);
        s6 = findViewById(R.id.S6RP);
        s7 = findViewById(R.id.S7RP);
        s8 = findViewById(R.id.S8RP);
        s9 = findViewById(R.id.S9RP);

        kode_jadwal.setText(detail.getKodeJadwal());
        tanggal.setText(detail.getTanggal());
        nama.setText(detail.getNamaPetugas());
        lokasi.setText(detail.getLokasi());
        shift.setText(detail.getShift());
        jam.setText(detail.getJam());
        l1.setText(detail.getLantai1());
        l2.setText(detail.getLantai2());
        l3.setText(detail.getLantai3());
        l4.setText(detail.getLantai4());
        l5.setText(detail.getLantai5());
        l6.setText(detail.getLantai6());
        l7.setText(detail.getLantai7());
        l8.setText(detail.getLantai8());
        l9.setText(detail.getLantaiBasement());
        ket.setText(detail.getKeterangan());

        if (detail.getFile1().equals("")) {
            f1.setText("Tidak Ada File");
        } else {
            String path = detail.getFile1();
            String lastNameFile = path.substring(path.lastIndexOf('/') + 1);
            f1.setText(lastNameFile);
            f1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path);
                }
            });
        }

        if (detail.getFile2().equals("")) {
            f2.setText("Tidak Ada File");
        } else {
            String path2 = detail.getFile2();
            String lastNameFile2 = path2.substring(path2.lastIndexOf('/') + 1);
            f2.setText(lastNameFile2);
            f2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path2);
                }
            });
        }

        if (detail.getFile3().equals("")) {
            f3.setText("Tidak Ada File");
        } else {
            String path3 = detail.getFile3();
            String lastNameFile3 = path3.substring(path3.lastIndexOf('/') + 1);
            f3.setText(lastNameFile3);
            f3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path3);
                }
            });
        }

        if (detail.getFile4().equals("")) {
            f4.setText("Tidak Ada File");

        } else {
            String path4 = detail.getFile4();
            String lastNameFile4 = path4.substring(path4.lastIndexOf('/') + 1);
            f4.setText(lastNameFile4);
            f4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path4);
                }
            });
        }

        if (detail.getFile5().equals("")) {
            f5.setText("Tidak Ada File");
        } else {
            String path5 = detail.getFile5();
            String lastNameFile5 = path5.substring(path5.lastIndexOf('/') + 1);
            f5.setText(lastNameFile5);
            f5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path5);
                }
            });
        }

        if (detail.getFile6().equals("")) {
            f6.setText("Tidak Ada File");

        } else {
            String path6 = detail.getFile6();
            String lastNameFile6 = path6.substring(path6.lastIndexOf('/') + 1);
            f6.setText(lastNameFile6);
            f6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path6);
                }
            });
        }


        if (detail.getFile7().equals("")) {
            f7.setText("Tidak Ada File");

        } else {
            String path7 = detail.getFile7();
            String lastNameFile7 = path7.substring(path7.lastIndexOf('/') + 1);
            f7.setText(lastNameFile7);
            f7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path7);
                }
            });
        }

        if (detail.getFile8().equals("")) {
            f8.setText("Tidak Ada File");

        } else {
            String path8 = detail.getFile8();
            String lastNameFile8 = path8.substring(path8.lastIndexOf('/') + 1);
            f8.setText(lastNameFile8);
            f8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path8);
                }
            });
        }

        if (detail.getFileBasement().equals("")) {
            f9.setText("Tidak Ada File");

        } else {
            String path9 = detail.getFileBasement();
            String lastNameFile9 = path9.substring(path9.lastIndexOf('/') + 1);
            f9.setText(lastNameFile9);
            f9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile(path9);
                }
            });
        }


        if (nama_gedung.equals("Gedung A1") || nama_gedung.equals("Gedung A2")) {
            r6.setVisibility(View.GONE);
            r7.setVisibility(View.GONE);
            r8.setVisibility(View.GONE);
            r9.setVisibility(View.GONE);
            s6.setVisibility(View.GONE);
            s7.setVisibility(View.GONE);
            s8.setVisibility(View.GONE);
            s9.setVisibility(View.GONE);
        } else if (nama_gedung.equals("Gedung B1") || nama_gedung.equals("Gedung B2")) {
            r4.setVisibility(View.GONE);
            r5.setVisibility(View.GONE);
            r6.setVisibility(View.GONE);
            r7.setVisibility(View.GONE);
            r8.setVisibility(View.GONE);
            r9.setVisibility(View.GONE);
            s4.setVisibility(View.GONE);
            s5.setVisibility(View.GONE);
            s6.setVisibility(View.GONE);
            s7.setVisibility(View.GONE);
            s8.setVisibility(View.GONE);
            s9.setVisibility(View.GONE);
        }

        initToolbar();


    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(null);

    }

    private void downloadFile(String mFile) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> downloadImage = apiInterface.downloadFile(mFile);
        downloadImage.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    boolean suc = writeResponseBodyToDisk(response.body(), mFile);
                    if (suc) {
                        String path = mFile.substring(mFile.lastIndexOf("."));
                        String name = mFile.substring(mFile.lastIndexOf('/') + 1);
                        String foler = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + name;
                        if (path.equals(".mp4")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "video/mp4");
                            startActivity(intent);
                        } else if (path.equals(".jpg") || path.equals(".jpeg") || path.equals(".png")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "image/*");
                            startActivity(intent);
                        } else if (mFile.equals("Tidak ada file pendukung")) {
                            Toast.makeText(DetailRiwayatPatroli.this, "Tidak ada file", Toast.LENGTH_SHORT).show();
                        } else if (mFile.equals(".")) {
                            Toast.makeText(DetailRiwayatPatroli.this, "Tidak ada file", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "*/*");
                            startActivity(intent);
                        }


                    } else {

                    }

                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailRiwayatPatroli.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String path) {
        String getName = path.substring(path.lastIndexOf('/') + 1);
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    getName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}