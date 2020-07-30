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
import com.mobile.spk.model.Jadwal;

import java.util.List;

public class TableAdapterJadwalPetugas extends RecyclerView.Adapter {

    private Context context;
    private List<Jadwal> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterJadwalPetugas(Context context, List<Jadwal> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.jadwal_petugas_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.WHITE);
            rowViewHolder.txtKode.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);



            rowViewHolder.txtKode.setText("Kode");
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtNama.setText("Petugas");


        } else {
            Jadwal modal = listData.get(rowPos - 1);

            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);



            rowViewHolder.txtNama.setTextColor(Color.BLACK);
            rowViewHolder.txtKode.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);



            rowViewHolder.txtKode.setText(modal.getKodeJadwal() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtNama.setText(modal.getNamaPetugas() + "");


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
        TextView txtKode;
        RelativeLayout rvItemsJadwalPetugas;
        TableAdapterJadwalPetugas.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
          
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtKode= itemView.findViewById(R.id.txt_kode);
            txtNama = itemView.findViewById(R.id.txt_petugas);
            rvItemsJadwalPetugas = itemView.findViewById(R.id.rv_item_jadwal_petugas);
            mListener = listener;
            rvItemsJadwalPetugas.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_jadwal_petugas:
                    mListener.onRowClick(rvItemsJadwalPetugas, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
