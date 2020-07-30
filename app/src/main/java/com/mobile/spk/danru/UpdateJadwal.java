
package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Petugas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateJadwal extends AppCompatActivity {

    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    AutoCompleteTextView gedung, petugas, shift;
    Button btnSubmit;
    EditText e_tanggal, e_kode_jadwal;


    private ArrayList<String> petugasList = new ArrayList<>();
    private List<Petugas> dataList = new ArrayList<>();
    private String[] gedung_item = {"Gedung A1","Gedung A2","Gedung B1", "Gedung B2", "Gedung C"};
    private String[] shift_item = {"Shift 1","Shift 2","Libur"};

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;


    String id_user, lokasiName, shiftName, sTanggal;
    String kode_shift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        String kode = getIntent().getStringExtra("kode");
        String nama = getIntent().getStringExtra("nama");
        String lokasi = getIntent().getStringExtra("lokasi");
        String tanggal = getIntent().getStringExtra("tanggal");
        String shifts = getIntent().getStringExtra("shift");
        String[] kodes = kode.split("JD");



        progressDialog = new ProgressDialog(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        gedung = (AutoCompleteTextView) findViewById(R.id.in_gedung);
        gedung.setText(lokasi);
        petugas = (AutoCompleteTextView) findViewById(R.id.in_petugas);
        petugas.setText(nama);
        shift = (AutoCompleteTextView) findViewById(R.id.in_shift_jadwal);
        shift.setText(shifts);
        e_kode_jadwal = findViewById(R.id.in_kode_jadwal);
        e_kode_jadwal.setText(kode);

        e_tanggal = findViewById(R.id.in_tgl_jadwal);
        e_tanggal.setText(tanggal);
        e_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(e_tanggal);
            }
        });

        btnSubmit = findViewById(R.id.btn_submit_jadwal);

        petugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_user = dataList.get(position).getId_user();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lokasiName = gedung.getText().toString();
                shiftName = shift.getText().toString();
                sTanggal = e_tanggal.getText().toString();


                if(shiftName.equals("Libur")){
                    kode_shift = "0";
                }else if(shiftName.equals("Shift 1")){
                    kode_shift = "1";
                }else if(shiftName.equals("Shift 2")){
                    kode_shift = "2";
                }

                if(id_user.equals("") || sTanggal.equals("") || lokasiName.equals("") || shiftName.equals("") ){
                    Toast.makeText(UpdateJadwal.this, "Gagal, silahkan isi semua field data", Toast.LENGTH_SHORT).show();
                }else{
                    updateJadwal(kodes[1].trim(),id_user, sTanggal, lokasiName, kode_shift);
                }








            }
        });

        getPetugas(petugas,nama);
        getSpinner(gedung, gedung_item);
        getSpinner(shift, shift_item);



    }
    private void getPetugas(AutoCompleteTextView target,String namas){
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        Call<ResponseBody> getPetugas = apiInterface.getPetugas();
        getPetugas.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        JSONArray a = o.getJSONArray("petugas");
                        for(int i = 0; i<a.length();i++){


                            JSONObject ao = a.getJSONObject(i);


                            petugasList.add(ao.getString("nama_lengkap"));
                            dataList.add(new Petugas(ao.getString("id_user"), ao.getString("nama_lengkap")));

                        }
                        int position = dataList.indexOf(namas);
                        id_user = dataList.get(position+1).getId_user();
                        getSpinnerAPI(target,petugasList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void updateJadwal(String kode, String id_user, String sTanggal, String lokasiName, String kode_shift) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> inputJadwal = apiInterface.updateJadwal(id_user,sTanggal,lokasiName, kode_shift,kode);
        inputJadwal.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(UpdateJadwal.this, "Jadwal berhasil di update", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateJadwal.this, JadwalHariIniActivity.class));
                        }else{
                            Toast.makeText(UpdateJadwal.this, "Terjadi kesalahan internal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(UpdateJadwal.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdateJadwal.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void showDateDialog(final EditText edt_target) {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edt_target.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }



    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,item);
        target.setAdapter(adapter);
    }

    private void getSpinnerAPI(AutoCompleteTextView target, ArrayList<String> item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }
}