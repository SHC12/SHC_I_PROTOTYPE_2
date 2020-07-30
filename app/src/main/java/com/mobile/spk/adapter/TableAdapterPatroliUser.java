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
import com.mobile.spk.model.PatroliModel;

import java.util.List;

public class TableAdapterPatroliUser extends RecyclerView.Adapter {

    private Context context;
    private List<PatroliModel> patroli;
    private RecyclerViewClickListener mListener;


    public TableAdapterPatroliUser(Context context, List<PatroliModel> patroli, RecyclerViewClickListener listener) {
        this.context = context;
        this.patroli = patroli;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.patroli_user_items, parent, false);

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
            rowViewHolder.txtStatus.setText("SP");
        } else {
            PatroliModel modal = patroli.get(rowPos - 1);

            rowViewHolder.txtKode.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtTanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtStatus.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtSF.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtKode.setTextColor(Color.BLACK);
            rowViewHolder.txtTanggal.setTextColor(Color.BLACK);
            rowViewHolder.txtStatus.setTextColor(Color.BLACK);
            rowViewHolder.txtSF.setTextColor(Color.BLACK);
            rowViewHolder.txtKode.setText(modal.getKodeJadwal() + "");
            rowViewHolder.txtTanggal.setText(modal.getTanggal()+"");
            rowViewHolder.txtStatus.setText(modal.getPatroli()+"");
            rowViewHolder.txtSF.setText(modal.getShift()+"");

        }
    }

    @Override
    public int getItemCount() {
        return patroli.size() + 1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtKode;
        TextView txtTanggal;
        TextView txtStatus;
        TextView txtSF;
        RelativeLayout rvItemsPatroli;
        RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            txtKode = itemView.findViewById(R.id.txt_kode);
            txtTanggal = itemView.findViewById(R.id.txt_tanggal);
            txtStatus = itemView.findViewById(R.id.txt_sp);
            txtSF = itemView.findViewById(R.id.txt_sf);
            mListener = listener;
            rvItemsPatroli = itemView.findViewById(R.id.rv_item_patroli_user);
            rvItemsPatroli.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_patroli_user:
                    mListener.onRowClick(rvItemsPatroli, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }
}
