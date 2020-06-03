package com.mobile.spk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.spk.R;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.JadwalDetailUmum;

import java.util.List;

public class TableAdapterJadwalUmumPergedung extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalDetailUmum> listData;

    public TableAdapterJadwalUmumPergedung(Context context, List<JadwalDetailUmum> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_detail_jadwal_umum_items, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtShift.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtShift.setTextColor(Color.WHITE);
            rowViewHolder.txtNama.setTextColor(Color.WHITE);


            rowViewHolder.txtShift.setText("Shift");
            rowViewHolder.txtNama.setText("Nama Petugas");

        } else {
            JadwalDetailUmum modal = listData.get(rowPos - 1);

            rowViewHolder.txtShift.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtShift.setTextColor(Color.BLACK);
            rowViewHolder.txtNama.setTextColor(Color.BLACK);

            rowViewHolder.txtShift.setText(modal.getShift() + "");
            rowViewHolder.txtNama.setText(modal.getNama_petugas()+"");


        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder{
        TextView txtShift;
        TextView txtNama;


        RowViewHolder(View itemView) {
            super(itemView);
            txtShift = itemView.findViewById(R.id.txt_shift);
            txtNama = itemView.findViewById(R.id.txt_nama_petugas);

        }


    }
}
