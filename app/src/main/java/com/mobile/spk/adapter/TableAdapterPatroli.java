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
import com.mobile.spk.model.JadwalTahunUser;
import com.mobile.spk.model.PatroliModel;

import java.util.List;

public class TableAdapterPatroli extends RecyclerView.Adapter {

    private Context context;
    private List<PatroliModel> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterPatroli(Context context, List<PatroliModel> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_patroli_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtSP.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtKode.setTextColor(Color.WHITE);
            rowViewHolder.txtTanggal.setTextColor(Color.WHITE);
            rowViewHolder.txtSF.setTextColor(Color.WHITE);
            rowViewHolder.txtSP.setTextColor(Color.WHITE);


            rowViewHolder.txtKode.setText("Kode");
            rowViewHolder.txtTanggal.setText("Tanggal");
            rowViewHolder.txtSF.setText("SF");
            rowViewHolder.txtSP.setText("SP");

        } else {
            PatroliModel modal = listData.get(rowPos - 1);

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSP.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtKode.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtSF.setTextColor(Color.BLACK);
            rowViewHolder.txtSP.setTextColor(Color.BLACK);

            rowViewHolder.txtKode.setText(modal.getKodeJadwal() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtSF.setText(modal.getShift()+"");
            rowViewHolder.txtSP.setText(modal.getPatroli()+"");



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
        TextView txtKode;
        TextView txtTanggal;
        TextView txtSF;
        TextView txtSP;
        RelativeLayout rvItemsPatroli;
        TableAdapterPatroli.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txt_kode_patroli);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal_patroli);
            txtSF = itemView.findViewById(R.id.txt_sf_patroli);
            txtSP = itemView.findViewById(R.id.txt_sp_patroli);
            rvItemsPatroli = itemView.findViewById(R.id.rv_item_patroli_click);
            mListener = listener;
            rvItemsPatroli.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_patroli_click:
                    mListener.onRowClick(rvItemsPatroli, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
