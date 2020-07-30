package com.mobile.spk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.spk.R;
import com.mobile.spk.model.AbsenHariIni;
import com.mobile.spk.model.CutiDanru;

import java.util.List;

public class TableAdapterAbsenHariIni extends RecyclerView.Adapter {

    private Context context;
    private List<AbsenHariIni> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterAbsenHariIni(Context context, List<AbsenHariIni> listData) {
        this.context = context;
        this.listData = listData;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_absen_hari_ini, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSf.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSt.setBackgroundResource(R.drawable.table_header_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.WHITE);
            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtSf.setTextColor(Color.WHITE);
            rowViewHolder.txtSt.setTextColor(Color.WHITE);



            rowViewHolder.txtNama.setText("Nama");
            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtSf.setText("SF");
            rowViewHolder.txtSt.setText("ST");


        } else {
            AbsenHariIni modal = listData.get(rowPos - 1);

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSf.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSt.setBackgroundResource(R.drawable.table_content_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.BLACK);
            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtSf.setTextColor(Color.BLACK);
            rowViewHolder.txtSt.setTextColor(Color.BLACK);


            rowViewHolder.txtNama.setText(modal.getNama() + "");
            rowViewHolder.txtNo.setText(modal.getNo()+"");
            rowViewHolder.txtSf.setText(modal.getSf()+"");
            rowViewHolder.txtSt.setText(modal.getSt()+"");



        }
    }

    @Override
    public int getItemCount() {
        return listData.size()+1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        TextView txtNo;
        TextView txtNama;
        TextView txtSf;
        TextView txtSt;

      

        RowViewHolder(View itemView) {
            super(itemView);
          
            txtNo = itemView.findViewById(R.id.txt_no);
            txtNama = itemView.findViewById(R.id.txt_nama_petugas);
            txtSf = itemView.findViewById(R.id.txt_sf);
            txtSt = itemView.findViewById(R.id.txt_st);

          
         

        }


       
    }

}
