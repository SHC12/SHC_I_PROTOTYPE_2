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
import com.mobile.spk.model.Informasi;

import java.util.List;

public class TableAdapterInformasi extends RecyclerView.Adapter {

    private Context context;
    private List<Informasi> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterInformasi(Context context, List<Informasi> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_informasi_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtJudul.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtJudul.setTextColor(Color.WHITE);


            
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtJudul.setText("Status");

        } else {
            Informasi modal = listData.get(rowPos - 1);

           
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtJudul.setBackgroundResource(R.drawable.table_content_cell_bg);


          
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtJudul.setTextColor(Color.BLACK);

         
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtJudul.setText(modal.getJudul_informasi()+"");


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
        TextView txtJudul;
        RelativeLayout rvItemsInformasi;
        TableAdapterInformasi.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
         
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            rvItemsInformasi = itemView.findViewById(R.id.rv_item_informasi);
            mListener = listener;
            rvItemsInformasi.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_informasi:
                    mListener.onRowClick(rvItemsInformasi, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
