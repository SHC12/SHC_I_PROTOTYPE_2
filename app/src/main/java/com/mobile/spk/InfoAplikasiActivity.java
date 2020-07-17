package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoAplikasiActivity extends AppCompatActivity {
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aplikasi);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        TextView info = (TextView) findViewById(R.id.info_aplkasi);
        TextView creator = (TextView) findViewById(R.id.creator_aplkasi);

        getinfo(info,creator);
    }

    private void getinfo(TextView info,TextView creator) {
    Call<ResponseBody> getInfo = apiInterface.getInfoApps();
    getInfo.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response.isSuccessful()){
                try {
                    JSONObject o = new JSONObject(response.body().string());
                    if(o.getString("status").equals("1")){
                        String mInfo = o.getString("info_aplikasi");
                        String mCreator = o.getString("creator");
                        info.setText(mInfo);
                        creator.setText(mCreator);
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
            Toast.makeText(InfoAplikasiActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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
}

