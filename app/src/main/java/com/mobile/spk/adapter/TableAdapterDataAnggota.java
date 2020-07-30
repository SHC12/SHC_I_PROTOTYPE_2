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
import com.mobile.spk.model.PatroliModel;

import java.util.List;

public class TableAdapterDataAnggota extends RecyclerView.Adapter {

    private Context context;
    private List<DataAnggota> listData;
    private RecyclerViewClickListener mListener;

    public TableAdapterDataAnggota(Context context, List<DataAnggota> listData, RecyclerViewClickListener mListener) {
        this.context = context;
        this.listData = listData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.riwayat_data_anggota_items, parent, false);

        return new RowViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtJabatan.setBackgroundResource(R.drawable.table_header_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.WHITE);
            rowViewHolder.txtNama.setTextColor(Color.WHITE);
            rowViewHolder.txtJabatan.setTextColor(Color.WHITE);


            rowViewHolder.txtNo.setText("No");
            rowViewHolder.txtNama.setText("Nama");
            rowViewHolder.txtJabatan.setText("Jabatan");

        } else {
            DataAnggota modal = listData.get(rowPos - 1);

            rowViewHolder.txtNo.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtJabatan.setBackgroundResource(R.drawable.table_content_cell_bg);


            rowViewHolder.txtNo.setTextColor(Color.BLACK);
            rowViewHolder.txtNama.setTextColor(Color.BLACK);
            rowViewHolder.txtJabatan.setTextColor(Color.BLACK);

            rowViewHolder.txtNo.setText(modal.getNo() + "");
            rowViewHolder.txtNama.setText(modal.getNama()+"");
            rowViewHolder.txtJabatan.setText(modal.getJabatan()+"");


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
        TextView txtNo;
        TextView txtNama;
        TextView txtJabatan;
        RelativeLayout rvItemsDataAnggota;
        TableAdapterDataAnggota.RecyclerViewClickListener mListener;

        RowViewHolder(View itemView,  RecyclerViewClickListener listener) {
            super(itemView);
            txtNo = itemView.findViewById(R.id.txt_no);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtJabatan = itemView.findViewById(R.id.txt_jabatan);
            rvItemsDataAnggota = itemView.findViewById(R.id.rv_item_data_anggota);
            mListener = listener;
            rvItemsDataAnggota.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_item_data_anggota:
                    mListener.onRowClick(rvItemsDataAnggota, getAdapterPosition());
                    break;

                default:
                    break;
            }

        }
    }

}
