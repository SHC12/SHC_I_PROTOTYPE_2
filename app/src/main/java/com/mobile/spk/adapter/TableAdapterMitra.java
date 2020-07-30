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
import com.mobile.spk.model.DataAnggota;
import com.mobile.spk.model.Mitra;

import java.util.List;

public class TableAdapterMitra extends RecyclerView.Adapter {

    private Context context;
    private List<Mitra> listData;
    private RecyclerViewClickListener listener;


    public TableAdapterMitra(Context context, List<Mitra> listData,RecyclerViewClickListener listener) {
        this.context = context;
        this.listData = listData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_mitra_items, parent, false);

        return new RowViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
//            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtNama.setTextColor(Color.WHITE);
//            rowViewHolder.txtLokasi.setTextColor(Color.WHITE);


            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtNama.setText("Nama");
//            rowViewHolder.txtLokasi.setText("Lokasi");

        } else {
            Mitra modal = listData.get(rowPos - 1);

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtLokasi.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtNama.setTextColor(Color.BLACK);
//            rowViewHolder.txtLokasi.setTextColor(Color.BLACK);

            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtNama.setText(modal.getNama()+"");
//            rowViewHolder.txtLokasi.setText(modal.getLokasi()+"");


        }
    }

    @Override
    public int getItemCount() {
        return listData.size()+1;
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNo;
        TextView txtNama;
        RelativeLayout relativeLayout;
        RecyclerViewClickListener mListener;

//        TextView txtLokasi;



        RowViewHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            txtNo = itemView.findViewById(R.id.txt_no);
            txtNama = itemView.findViewById(R.id.txt_nama);
            relativeLayout = itemView.findViewById(R.id.rv_item_mitraa);
            mListener = listener;
            relativeLayout.setOnClickListener(this);

//            txtLokasi = itemView.findViewById(R.id.txt_lokasi);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_mitraa:
                    mListener.onRowClick(relativeLayout, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
