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
import com.mobile.spk.model.JadwalBulanUser;

import java.util.List;

public class TableAdapterDetailPersonal extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalBulanUser> listData;

    public TableAdapterDetailPersonal(Context context, List<JadwalBulanUser> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_detail_jadwal_personal_items, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {


            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtShift.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtShift.setTextColor(Color.WHITE);
            rowViewHolder.txtLokasi.setTextColor(Color.WHITE);


            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtShift.setText("Shift");
            rowViewHolder.txtLokasi.setText("Lokasi");
        } else {
            JadwalBulanUser modal = listData.get(rowPos - 1);


            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtShift.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtShift.setTextColor(Color.BLACK);
            rowViewHolder.txtLokasi.setTextColor(Color.BLACK);

            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtShift.setText(modal.getShift()+"");
            rowViewHolder.txtLokasi.setText(modal.getLokasi()+"");

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder{

        TextView txtTanggal;
        TextView txtShift;
        TextView txtLokasi;

        RowViewHolder(View itemView) {
            super(itemView);
            ;
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtShift = itemView.findViewById(R.id.txt_shift);
            txtLokasi = itemView.findViewById(R.id.txt_lokasi);
        }


    }
}
