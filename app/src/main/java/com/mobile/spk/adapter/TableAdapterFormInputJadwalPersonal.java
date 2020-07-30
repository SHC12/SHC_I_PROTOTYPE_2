package com.mobile.spk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.spk.R;
import com.mobile.spk.anggota.DetailJadwalUmumPergedung;
import com.mobile.spk.danru.JadwalPersonalDanru;
import com.mobile.spk.model.JadwalBulanUmum;
import com.mobile.spk.model.JadwalPersonalDanruModel;
import com.mobile.spk.model.JadwalPersonalModel;

import java.util.List;

public class TableAdapterFormInputJadwalPersonal extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalPersonalModel> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterFormInputJadwalPersonal(Context context, List<JadwalPersonalModel> listData) {
        this.context = context;
        this.listData = listData;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_input_jadwal_personal_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        String gedung[] = {"Pilih Lokasi","Gedung A","Gedung B","Gedung C"};
        String shift[] = {"Pilih Shift","0","1","2"};

        String shiftText[] = {"Shift"};
        String lokasiText[] = {"Lokasi"};
        int rowPos = rowViewHolder.getAdapterPosition();

        if(rowPos == 0){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, lokasiText);
            ArrayAdapter<String> adapterShift = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, shiftText);

            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.sShift.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtTanggal.setBackgroundColor(Color.WHITE);
            rowViewHolder.txtLokasi.setBackgroundColor(Color.WHITE);
            rowViewHolder.sShift.setBackgroundColor(Color.WHITE);

            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtLokasi.setAdapter(adapter);
            rowViewHolder.txtLokasi.setSelection(adapter.getPosition("Lokasi"));
            rowViewHolder.sShift.setAdapter(adapterShift);
            rowViewHolder.sShift.setSelection(adapterShift.getPosition("Shift"));
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, gedung);
            ArrayAdapter<String> adapterShift = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, shift);
            JadwalPersonalModel modal = listData.get(rowPos);

            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.sShift.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setText(modal.getTanggal() + "");
            rowViewHolder.txtLokasi.setAdapter(adapter);
            rowViewHolder.txtLokasi.setSelection(adapter.getPosition("Pilih Lokasi"));

            rowViewHolder.sShift.setAdapter(adapterShift);
            rowViewHolder.sShift.setSelection(adapterShift.getPosition("Pilih Shift"));


        }

        }


    @Override
    public int getItemCount() {
        return listData.size();
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtTanggal;
        Spinner txtLokasi, sShift;


        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtLokasi = itemView.findViewById(R.id.txt_lokasi);
            sShift = itemView.findViewById(R.id.txt_shift);

        }


        @Override
        public void onClick(View v) {


        }
    }

}
