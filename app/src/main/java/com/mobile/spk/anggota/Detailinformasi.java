package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Informasi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailinformasi extends AppCompatActivity {

    public static final String DETAIL_INFORMASI = "detail_informasi" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinformasi);

        TextView tanggal = (TextView) findViewById(R.id.tanggalDetaiInfo);
        TextView judul = (TextView) findViewById(R.id.judulDetailInfo);
        TextView sumber = (TextView) findViewById(R.id.sumberDetailInfo);
        TextView isi = (TextView) findViewById(R.id.isiDetailInfo);
        TextView file = (TextView) findViewById(R.id.fileDetailInfo);

        Informasi informasi = getIntent().getParcelableExtra(DETAIL_INFORMASI);
        tanggal.setText(informasi.getTanggal());
        judul.setText(informasi.getJudul_informasi());
        sumber.setText(informasi.getNama_user());
        isi.setText(informasi.getDetail_informasi());
        String path = informasi.getFile();
        String lastNameFile = path.substring(path.lastIndexOf('/')+1);
        file.setText(lastNameFile);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            downloadFile(path);
            }
        });

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
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    boolean suc = writeResponseBodyToDisk(response.body(),mFile);
                    if(suc){
                        String path = mFile.substring(mFile.lastIndexOf("."));
                        String name = mFile.substring(mFile.lastIndexOf('/')+1);
                        String foler = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+name;
                        if(path.equals(".mp4")){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "video/mp4");
                            startActivity(intent);
                        }else if(path.equals(".jpg")||path.equals(".jpeg")||path.equals(".png")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "image/*");
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(foler), "*/*");
                            startActivity(intent);
                        }


                    }else{

                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(Detailinformasi.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailinformasi.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String path) {
        String getName = path.substring(path.lastIndexOf('/')+1);
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