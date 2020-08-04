package com.mobile.spk.anggota;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mobile.spk.FilePath;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.InformasiActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.PatroliModel;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormGedung extends AppCompatActivity {

    private SessionManager sessionManager;
    public static final String DETAIL_PATROLI = "detail_patroli";
    private LinearLayout lBasement,l1,l2,l3,l4,l5,l6,l7,l8,l1b,l2b;
    private LinearLayout step1, step2, step3;
    private AutoCompleteTextView aL1,aL2,aL3,aL4,aL5,aL6,aL7,aL8,aL1b,aL2b,aLbasement,gedung;
    private String[] status_gedung = {"Aman","Mencurigakan","Tidak Aman","Bahaya"};
    private String[] nama_gedungA = {"Gedung A1","Gedung A2"};
    private String[] nama_gedungB = {"Gedung B1","Gedung B2"};

    EditText e_kode_jadwal, e_tanggal, e_gedung, e_jam, f_lt1, f_lt2, f_lt3, f_lt4, f_lt5, f_lt6, f_lt7, f_lt8, f_basement, e_keterangan;
    Button btnLaporan;
    String nama_gedung, kode, kode_jadwall, jam;

    TimePickerDialog timePickerDialog;

    String path_file,getSize;
    File f_l1, f_l2, f_l3, f_l4, f_l5, f_l6, f_l7, f_l8, f_base;
    private boolean fileFoto = false;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gedung);
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        PatroliModel data = getIntent().getParcelableExtra(DETAIL_PATROLI);
        id_user = user.get(SessionManager.ID);

        e_kode_jadwal = findViewById(R.id.in_kode_jadwal_patroli);
        e_gedung = findViewById(R.id.in_nama_gedung_patroli);
        e_tanggal = findViewById(R.id.in_tanggal_laporan);
        e_jam = findViewById(R.id.in_jam_patroli);
        f_lt1 = findViewById(R.id.in_file_lantai_1);
        f_lt2 = findViewById(R.id.in_file_lantai_2);
        f_lt3 = findViewById(R.id.in_file_lantai_3);
        f_lt4 = findViewById(R.id.in_file_lantai_4);
        f_lt5 = findViewById(R.id.in_file_lantai_5);
        f_lt6 = findViewById(R.id.in_file_lantai_6);
        f_lt7 = findViewById(R.id.in_file_lantai_7);
        f_lt8 = findViewById(R.id.in_file_lantai_8);
        f_basement = findViewById(R.id.in_file_basement);
        e_keterangan = findViewById(R.id.in_tambahan);
        btnLaporan = findViewById(R.id.btnbuatLaporan);

        f_lt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 41);
            }
        });

        f_lt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 42);
            }
        });

        f_lt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 43);
            }
        });

        f_lt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 44);
            }
        });

        f_lt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 45);
            }
        });

        f_lt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 46);
            }
        });

        f_lt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 47);
            }
        });

        f_lt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 48);
            }
        });

        f_basement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 49);
            }
        });


        aL1 = findViewById(R.id.in_lantai_1);
        getAdapter(aL1,status_gedung);
        aL2 = findViewById(R.id.in_lantai_2);
        getAdapter(aL2,status_gedung);
        aL3 = findViewById(R.id.in_lantai_3);
        getAdapter(aL3,status_gedung);
        aL4 = findViewById(R.id.in_lantai_4);
        getAdapter(aL4,status_gedung);
        aL5 = findViewById(R.id.in_lantai_5);
        getAdapter(aL5,status_gedung);
        aL6 = findViewById(R.id.in_lantai_6);
        getAdapter(aL6,status_gedung);
        aL7 = findViewById(R.id.in_lantai_7);
        getAdapter(aL7,status_gedung);
        aL8 = findViewById(R.id.in_lantai_8);
        getAdapter(aL8,status_gedung);

        aLbasement = findViewById(R.id.in_basement);
        getAdapter(aLbasement,status_gedung);

        e_jam.setInputType(InputType.TYPE_NULL);
        e_jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePickerDialog = new TimePickerDialog(FormGedung.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                e_jam.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        e_kode_jadwal.setText(data.getKodeJadwal());
        e_gedung.setText(data.getLokasi());
        e_tanggal.setText(data.getTanggal());
        kode = data.getKodeNonFormat();
        kode_jadwall = data.getKodeJadwal();

        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jam = e_jam.getText().toString();
                String sKet = e_keterangan.getText().toString();
                String al1 = aL1.getText().toString();
                String al2 = aL2.getText().toString();
                String al3 = aL3.getText().toString();
                String al4 = aL4.getText().toString();
                String al5 = aL5.getText().toString();
                String al6 = aL6.getText().toString();
                String al7 = aL7.getText().toString();
                String al8 = aL8.getText().toString();
                String aBasemenet = aLbasement.getText().toString();

                String slokasi = e_gedung.getText().toString();

                    postPatroliGedung(kode_jadwall, id_user, kode, jam, sKet, al1, al2, al3, al4, al5, al6, al7, al8, aBasemenet, f_l1, f_l2, f_l3, f_l4, f_l5, f_l6, f_l7, f_l8, f_base );




            }
        });




        nama_gedung = data.getLokasi();
        getForm(nama_gedung);

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


    private void postPatroliGedung(String mKodeJadwal, String mId_user, String mKode, String mJam, String mKeterangan, String mL1, String mL2, String mL3, String mL4, String mL5, String mL6, String mL7, String mL8, String mBasement, File fL1, File fL2, File fL3, File fL4, File fL5, File fL6, File fL7, File fL8, File fL9) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        RequestBody rKodeJadwal = RequestBody.create(MediaType.parse("text/plain"), mKodeJadwal);
        RequestBody rIdUser = RequestBody.create(MediaType.parse("text/plain"), mId_user);
        RequestBody rKode = RequestBody.create(MediaType.parse("text/plain"), mKode);
        RequestBody rJam = RequestBody.create(MediaType.parse("text/plain"), mJam);
        RequestBody rKeterangan = RequestBody.create(MediaType.parse("text/plain"), mKeterangan);
        RequestBody rLt1 = RequestBody.create(MediaType.parse("text/plain"), mL1);
        RequestBody rLt2 = RequestBody.create(MediaType.parse("text/plain"), mL2);
        RequestBody rLt3 = RequestBody.create(MediaType.parse("text/plain"), mL3);
        RequestBody rLt4 = RequestBody.create(MediaType.parse("text/plain"), mL4);
        RequestBody rLt5 = RequestBody.create(MediaType.parse("text/plain"), mL5);
        RequestBody rLt6 = RequestBody.create(MediaType.parse("text/plain"), mL6);
        RequestBody rLt7 = RequestBody.create(MediaType.parse("text/plain"), mL7);
        RequestBody rLt8 = RequestBody.create(MediaType.parse("text/plain"), mL8);
        RequestBody rLt9 = RequestBody.create(MediaType.parse("text/plain"), mBasement);


        Map<String,RequestBody> partMapKeterangan = new HashMap<>();
        List<MultipartBody.Part> partMapLt1 = new ArrayList<>();
        if(!TextUtils.isEmpty(rKeterangan.toString())){
            partMapKeterangan.put("keterangan",createPartFromString(mKeterangan));
        }
        if(fL1 != null){
            partMapLt1.add(prepareFilePart("lt1_file",fL1));
        }
        if(fL2 != null){
            partMapLt1.add(prepareFilePart("lt2_file",fL2));
        }
        if(fL3 != null){
            partMapLt1.add(prepareFilePart("lt3_file",fL3));
        }
        if(fL4 != null){
            partMapLt1.add(prepareFilePart("lt4_file",fL4));

        }
        if(fL5 != null){
            partMapLt1.add(prepareFilePart("lt5_file",fL5));

        }
        if(fL6 != null){
            partMapLt1.add(prepareFilePart("lt6_file",fL6));

        }
        if(fL7 != null){
            partMapLt1.add(prepareFilePart("lt7_file",fL7));

        }
        if(fL8 != null){
            partMapLt1.add(prepareFilePart("lt8_file",fL8));
        }
        if(fL9 != null){
            partMapLt1.add(prepareFilePart("basement_file",fL9));
        }

        Call<ResponseBody> submitFile = apiInterface.postPatroliGedungC(rKodeJadwal,rIdUser, rKode,rJam,partMapKeterangan,rLt1, rLt2, rLt3, rLt4, rLt5, rLt6, rLt7, rLt8, rLt9, partMapLt1);
        submitFile.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(FormGedung.this, "Laporan berhasil di buat", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormGedung.this, PatroliActivity.class));
                        }else {
                            Toast.makeText(FormGedung.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FormGedung.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void getSpinner() {
        aL1 = findViewById(R.id.in_lantai_1);
        getAdapter(aL1,status_gedung);
        aL2 = findViewById(R.id.in_lantai_2);
        getAdapter(aL2,status_gedung);
        aL3 = findViewById(R.id.in_lantai_3);
        getAdapter(aL3,status_gedung);
        aL4 = findViewById(R.id.in_lantai_4);
        getAdapter(aL4,status_gedung);
        aL5 = findViewById(R.id.in_lantai_5);
        getAdapter(aL5,status_gedung);
        aL6 = findViewById(R.id.in_lantai_6);
        getAdapter(aL6,status_gedung);
        aL7 = findViewById(R.id.in_lantai_7);
        getAdapter(aL7,status_gedung);
        aL8 = findViewById(R.id.in_lantai_8);
        getAdapter(aL8,status_gedung);

        aLbasement = findViewById(R.id.in_basement);
        getAdapter(aLbasement,status_gedung);

    }

    private void getAdapter(AutoCompleteTextView at,String[] status){
      ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,status);
        at.setAdapter(adapter);
    }


    private void getForm(String flagGedung) {
        lBasement = findViewById(R.id.line_basement);
        step1 = findViewById(R.id.line_step1);
        step2 = findViewById(R.id.line_step2);
        step3 = findViewById(R.id.line_step3);




        if(flagGedung.equals("Gedung A1") || flagGedung.equals("Gedung A2")){
            lBasement.setVisibility(View.GONE);
            step3.setVisibility(View.GONE);
        } else if(flagGedung.equals("Gedung B1") || flagGedung.equals("Gedung B2")){
            lBasement.setVisibility(View.GONE);
            step2.setVisibility(View.GONE);
            step3.setVisibility(View.GONE);
        }

    }
    private MultipartBody.Part prepareFilePart(String partName, File file){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(),requestBody);
    }

    public RequestBody createPartFromString(String string) {
        return RequestBody.create(MultipartBody.FORM, string);
    }
    public static long getFolderSizeLabel(File file) {
        long size = getFolderSize(file) / 1024; // Get size and convert bytes into Kb.
        if (size >= 1024) {
            return (size / 1024);
        } else {
            return 1;
        }
    }
    public static long getFolderSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                size += getFolderSize(child);
            }
        } else {
            size = file.length();
        }
        return size;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 41:
                if (requestCode == 41 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l1 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l1);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt1.setText(f_l1.getName());


                }
                break;

             case 42:
                if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l2 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l2);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt2.setText(f_l2.getName());


                }
                break;

                case 43:
                if (requestCode == 43 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l3 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l3);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt3.setText(f_l3.getName());


                }
                break;

                case 44:
                if (requestCode == 44 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l4 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l4);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt4.setText(f_l4.getName());


                }
                break;

                case 45:
                if (requestCode == 45 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l5 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l5);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt5.setText(f_l5.getName());


                }
                break;

                case 46:
                if (requestCode == 46 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l6 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l6);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt6.setText(f_l6.getName());


                }
                break;

                case 47:
                if (requestCode == 47 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l7 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l7);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt7.setText(f_l7.getName());


                }
                break;

                case 48:
                if (requestCode == 48 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_l8 = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_l8);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_lt8.setText(f_l8.getName());


                }
                break;

                case 49:
                if (requestCode == 49 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormGedung.this, uri);
                    f_base = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(f_base);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    f_basement.setText(f_base.getName());


                }
                break;





        }
    }
}