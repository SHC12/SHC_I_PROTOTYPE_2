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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.spk.R;
import com.mobile.spk.anggota.DetailJadwalUmumPergedung;
import com.mobile.spk.model.JadwalBulanUmum;
import com.mobile.spk.model.JadwalTahunUmum;

import org.w3c.dom.Text;

import java.util.List;

public class TableAdapterJadwalBulanUmum extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalBulanUmum> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterJadwalBulanUmum(Context context, List<JadwalBulanUmum> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_jadwal_bulan_umum_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;
        String gedung[] = {"Pilih Lokasi","Gedung A","Gedung B","Gedung C"};
        int rowPos = rowViewHolder.getAdapterPosition();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item,gedung);
            JadwalBulanUmum modal = listData.get(rowPos);

            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setText(modal.getTanggal() + "");
            rowViewHolder.txtLokasi.setAdapter(adapter);
            rowViewHolder.txtLokasi.setSelection(adapter.getPosition("Pilih Lokasi"));
            rowViewHolder.txtLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(rowViewHolder.txtLokasi.getSelectedItem().toString().equals("Gedung A")){
                        context.startActivity(new Intent(context, DetailJadwalUmumPergedung.class));
                    } else if(rowViewHolder.txtLokasi.getSelectedItem().toString().equals("Gedung B")){
                        context.startActivity(new Intent(context, DetailJadwalUmumPergedung.class));
                    }else if(rowViewHolder.txtLokasi.getSelectedItem().toString().equals("Gedung C")){
                        context.startActivity(new Intent(context, DetailJadwalUmumPergedung.class));
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


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
        Spinner txtLokasi;
        RelativeLayout rvItemsJadwalPersonal;
        TableAdapterJadwalBulanUmum.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtLokasi = itemView.findViewById(R.id.txt_lokasi);
            rvItemsJadwalPersonal = itemView.findViewById(R.id.rv_item_jadwal_bulan_umum_user);
            mListener = listener;
            rvItemsJadwalPersonal.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_jadwal_bulan_umum_user:
                    mListener.onRowClick(rvItemsJadwalPersonal, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
