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
import com.mobile.spk.model.JadwalTahunUmum;
import com.mobile.spk.model.JadwalTahunUser;

import java.util.List;

public class TableAdapterJadwalTahunUmum extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalTahunUmum> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterJadwalTahunUmum(Context context, List<JadwalTahunUmum> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_jadwal_tahun_umum_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtBulan.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTahun.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtBulan.setTextColor(Color.WHITE);
            rowViewHolder.txtTahun.setTextColor(Color.WHITE);


            rowViewHolder.txtBulan.setText("Bulan");
            rowViewHolder.txtTahun.setText("Tahun");

        } else {
            JadwalTahunUmum modal = listData.get(rowPos - 1);

            rowViewHolder.txtBulan.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTahun.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtBulan.setTextColor(Color.BLACK);
            rowViewHolder.txtTahun.setTextColor(Color.BLACK);

            rowViewHolder.txtBulan.setText(modal.getBulan() + "");
            rowViewHolder.txtTahun.setText(modal.getTahun()+"");


        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtBulan;
        TextView txtTahun;
        RelativeLayout rvItemsJadwalPersonal;
        TableAdapterJadwalTahunUmum.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtBulan = itemView.findViewById(R.id.txt_bulan);
            txtTahun = itemView.findViewById(R.id.txt_tahun);
            rvItemsJadwalPersonal = itemView.findViewById(R.id.rv_item_jadwal_tahun_umum_user);
            mListener = listener;
            rvItemsJadwalPersonal.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_jadwal_tahun_umum_user:
                    mListener.onRowClick(rvItemsJadwalPersonal, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
