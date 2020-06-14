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

import java.util.List;

public class TableAdapterAbsenUser extends RecyclerView.Adapter {

    private Context context;
    private List<AbsenUser> absen;

    public TableAdapterAbsenUser(Context context, List<AbsenUser> absen) {
        this.context = context;
        this.absen = absen;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_absen_user_items, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtJam.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtJam.setTextColor(Color.WHITE);
            rowViewHolder.txtSF.setTextColor(Color.WHITE);
            rowViewHolder.txtStatus.setTextColor(Color.WHITE);

            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtJam.setText("Jam");
            rowViewHolder.txtSF.setText("SF");
            rowViewHolder.txtStatus.setText("ST");
        } else {
            AbsenUser modal = absen.get(rowPos - 1);

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtJam.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtJam.setTextColor(Color.BLACK);
            rowViewHolder.txtStatus.setTextColor(Color.BLACK);
            rowViewHolder.txtSF.setTextColor(Color.BLACK);
            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtJam.setText(modal.getJam()+"");
            rowViewHolder.txtStatus.setText(modal.getSF()+"");
            rowViewHolder.txtSF.setText(modal.getStatus()+"");

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder{
        TextView txtNo;
        TextView txtTanggal;
        TextView txtJam;
        TextView txtStatus;
        TextView txtSF;

        RowViewHolder(View itemView) {
            super(itemView);
            txtNo = itemView.findViewById(R.id.txt_no);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtJam = itemView.findViewById(R.id.txt_jam);
            txtStatus = itemView.findViewById(R.id.txt_st);
            txtSF = itemView.findViewById(R.id.txt_sf);
        }


    }
}
