package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.spk.R;
import com.mobile.spk.anggota.JadwalUmum;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Petugas;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalHariIniActivity extends AppCompatActivity {
    private String[] gedung_item = {"Gedung A1","Gedung A2","Gedung B1", "Gedung B2", "Gedung C"};
    private List<Petugas> dataList = new ArrayList<>();
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private ArrayList<String> petugasList = new ArrayList<>();
    AutoCompleteTextView gedung, petugas;
    Petugas model;
    private SessionManager sessionManager;
    String getGedung, getNamaPetugas;
    String getID_user;

    String triggerview;
    Button btnInputJadwal;

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_hari_ini);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        progressDialog = new ProgressDialog(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
         gedung = (AutoCompleteTextView) findViewById(R.id.in_jadwal_pilih_gedung);
         petugas = (AutoCompleteTextView) findViewById(R.id.in_jadwal_pilih_petugas);
         btnInputJadwal = findViewById(R.id.btnInputJadwalPetugas);

        level = Integer.parseInt(user.get(SessionManager.LEVEL));

        if(level != 2){
            btnInputJadwal.setVisibility(View.GONE);
        }
        petugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getID_user = dataList.get(position).getId_user();

            }
        });




        getSpinner(gedung, gedung_item);
        getPetugas(petugas);
    }

    public void toJadwalUmumDanru(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this, JadwalUmum.class));
    }

    public void toTampilGedung(View view) {
        getGedung = gedung.getText().toString();
        startActivity(new Intent(JadwalHariIniActivity.this, JadwalByGedung.class).putExtra("nama_gedung",getGedung));;

    }
    public void toTampilPetugas(View view) {

        getNamaPetugas = petugas.getText().toString();


        Intent in = new Intent(JadwalHariIniActivity.this, JadwalByPetugas.class);
        in.putExtra("id_user", getID_user);
        in.putExtra("nama_petugas", getNamaPetugas);
        startActivity(in);
    }


    public void toGedungA(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this,DetailJadwalHariIni.class));
    }

    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,item);
        target.setAdapter(adapter);
    }

    private void getSpinnerAPI(AutoCompleteTextView target, ArrayList<String> item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }

    private void getPetugas(AutoCompleteTextView target){
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


    public void toInputJadwalDanru(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this,FormInputJadwal.class));
    }
}