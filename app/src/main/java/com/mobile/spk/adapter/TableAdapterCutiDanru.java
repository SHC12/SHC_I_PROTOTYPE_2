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
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.CutiDanru;
import com.mobile.spk.model.PatroliModel;

import java.util.List;

public class TableAdapterCutiDanru extends RecyclerView.Adapter {

    private Context context;
    private List<Cuti> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterCutiDanru(Context context, List<Cuti> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_cuti_danru_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);



            rowViewHolder.txtNama.setText("Nama");
            rowViewHolder.txtTanggal.setText("Tanggal");


        } else {
            Cuti modal = listData.get(rowPos - 1);

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);


            rowViewHolder.txtNama.setText(modal.getNama_lengkap() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");



        }
    }

    @Override
    public int getItemCount() {
        return listData.size()+1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtTanggal;
        TextView txtNama;
        RelativeLayout rvItemsCuti;
        TableAdapterCutiDanru.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
          
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtNama = itemView.findViewById(R.id.txt_nama_petugas);
            rvItemsCuti = itemView.findViewById(R.id.rv_item_cuti_danru);
            mListener = listener;
            rvItemsCuti.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_cuti_danru:
                    mListener.onRowClick(rvItemsCuti, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
