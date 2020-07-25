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
import com.mobile.spk.model.AbsenUser;

import java.util.List;

public class TableAdapterAbsenUser extends RecyclerView.Adapter {

    private Context context;
    private List<AbsenUser> absen;
    private RecyclerViewClickListener mListener;


    public TableAdapterAbsenUser(Context context, List<AbsenUser> absen,RecyclerViewClickListener listener) {
        this.context = context;
        this.absen = absen;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.absen_user_items, parent, false);

        return new RowViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtKode.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtSF.setTextColor(Color.WHITE);
            rowViewHolder.txtStatus.setTextColor(Color.WHITE);

            rowViewHolder.txtKode.setText("Kode");
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtSF.setText("SF");
            rowViewHolder.txtStatus.setText("ST");
        } else {
            AbsenUser modal = absen.get(rowPos - 1);

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtKode.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtStatus.setTextColor(Color.BLACK);
            rowViewHolder.txtSF.setTextColor(Color.BLACK);
            rowViewHolder.txtKode.setText(modal.getKode() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtStatus.setText(modal.getStatus_absen()+"");
            rowViewHolder.txtSF.setText(modal.getStatus_shift()+"");

        }
    }

    @Override
    public int getItemCount() {
        return absen.size() + 1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtKode;
        TextView txtTanggal;
        TextView txtStatus;
        TextView txtSF;
        RelativeLayout rvItemsAbsen;
        RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txt_kode);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtStatus = itemView.findViewById(R.id.txt_st);
            txtSF = itemView.findViewById(R.id.txt_sf);
            mListener = listener;
            rvItemsAbsen = itemView.findViewById(R.id.rv_item_jadwal_user);
            rvItemsAbsen.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_jadwal_user:
                    mListener.onRowClick(rvItemsAbsen, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }
}
