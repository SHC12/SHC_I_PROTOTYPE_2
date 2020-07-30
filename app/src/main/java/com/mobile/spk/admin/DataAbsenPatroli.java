package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.R;
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

public class DataAbsenPatroli extends AppCompatActivity {
    private List<Petugas> dataList = new ArrayList<>();
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private ArrayList<String> petugasList = new ArrayList<>();
    String getID_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_absen_petugas);

        LinearLayout viewAbsensi = (LinearLayout) findViewById(R.id.view_absen_admin);
        LinearLayout viewPatroli = (LinearLayout) findViewById(R.id.view_patroli_admin);
        String state = getIntent().getStringExtra("menuAdmin");
        if(state.equals("absensi")){
            viewAbsensi.setVisibility(View.VISIBLE);
        }else{
            viewPatroli.setVisibility(View.VISIBLE);
        }

        MaterialButton btnToAbsensi = (MaterialButton) findViewById(R.id.btnAdminAbsensi);
        MaterialButton btnToPatroli = (MaterialButton) findViewById(R.id.btnAdminPatroli);
        progressDialog = new ProgressDialog(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        AutoCompleteTextView petugas = (AutoCompleteTextView) findViewById(R.id.in_jadwal_pilih_petugas);
        petugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getID_user = dataList.get(position).getId_user();
            }
        });

        btnToAbsensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
}