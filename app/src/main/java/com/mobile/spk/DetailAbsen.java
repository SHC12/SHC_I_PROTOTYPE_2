package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.R;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.utils.SessionManager;

import java.util.HashMap;

public class DetailAbsen extends AppCompatActivity {

    public static final String DETAIL_JADWAL = "detail_jadwal" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetail();
        TextView kode = (TextView) findViewById(R.id.kodeDetailAbsen);
        TextView nama = (TextView) findViewById(R.id.namaDetailAbsen);
        TextView tanggal = (TextView) findViewById(R.id.tanggalDetailAbsen);
        TextView lokasi = (TextView) findViewById(R.id.lokasiDetailAbsen);
        TextView shift = (TextView) findViewById(R.id.shiftDetailAbsen);
        TextView statusAbsen = (TextView) findViewById(R.id.statusAbsenDetailAbsen);
        TextView keterangan = (TextView) findViewById(R.id.keteranganDetailAbsen);


        AbsenUser absen = getIntent().getParcelableExtra(DETAIL_JADWAL);
        kode.setText(absen.getKode());
        nama.setText(absen.getNamaPetugas());
        tanggal.setText(absen.getTanggal());
        lokasi.setText(absen.getLokasi());
        shift.setText(absen.getStatus_shift());
        statusAbsen.setText(absen.getStatus_absen());
        keterangan.setText(absen.getKeterangan());


    }
}