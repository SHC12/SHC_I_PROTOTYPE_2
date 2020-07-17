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
import com.mobile.spk.model.JadwalTahunUmum;

import java.util.List;

public class TableAdapterPengajuanCuti extends RecyclerView.Adapter {

    private Context context;
    private List<Cuti> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterPengajuanCuti(Context context, List<Cuti> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_pengajuan_cuti, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtStatus.setTextColor(Color.WHITE);


            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtStatus.setText("Status");

        } else {
            Cuti modal = listData.get(rowPos - 1);

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtStatus.setTextColor(Color.BLACK);

            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtStatus.setText(modal.getStatus()+"");


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
        TextView txtNo;
        TextView txtTanggal;
        TextView txtStatus;
        RelativeLayout rvItemsCuti;
        TableAdapterPengajuanCuti.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtNo = itemView.findViewById(R.id.txt_no);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtStatus = itemView.findViewById(R.id.txt_status);
            rvItemsCuti = itemView.findViewById(R.id.rv_item_cuti);
            mListener = listener;
            rvItemsCuti.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_cuti:
                    mListener.onRowClick(rvItemsCuti, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
