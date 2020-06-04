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
import com.mobile.spk.model.JadwalPersonalDanruModel;

import java.util.List;

public class TableAdapterListJadwalPersonalDanru extends RecyclerView.Adapter {

    private Context context;
    private List<JadwalPersonalDanruModel> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterListJadwalPersonalDanru(Context context, List<JadwalPersonalDanruModel> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_jadwal_anggota_personal_items, parent, false);

        return new RowViewHolder(itemView, mListener);
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


            rowViewHolder.txtShift.setText("No");
            rowViewHolder.txtNama.setText("Nama Petugas");

        } else {
            JadwalPersonalDanruModel modal = listData.get(rowPos - 1);

            rowViewHolder.txtShift.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtShift.setTextColor(Color.BLACK);
            rowViewHolder.txtNama.setTextColor(Color.BLACK);

            rowViewHolder.txtShift.setText(modal.getNo() + "");
            rowViewHolder.txtNama.setText(modal.getNama()+"");


        }
    }

    @Override
    public int getItemCount() {
        return listData.size() + 1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtShift;
        TextView txtNama;
        RelativeLayout rvItemsJadwalPersonal;
        TableAdapterListJadwalPersonalDanru.RecyclerViewClickListener mListener;


        RowViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            txtShift = itemView.findViewById(R.id.txt_no);
            txtNama = itemView.findViewById(R.id.txt_nama_petugas);
            rvItemsJadwalPersonal = itemView.findViewById(R.id.rv_item_jadwal_personal_danru);
            mListener = listener;
            rvItemsJadwalPersonal.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_jadwal_personal_danru:
                    mListener.onRowClick(rvItemsJadwalPersonal, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }
}
