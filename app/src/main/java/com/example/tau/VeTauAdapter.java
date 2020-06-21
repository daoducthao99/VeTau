package com.example.tau;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.annotation.Nullable;
public class VeTauAdapter extends RecyclerView.Adapter<VeTauAdapter.VeTauHolder> {
    private ArrayList<VeTau> data;
    private LayoutInflater inflater;
    private FaceItemListener listener;

    public VeTauAdapter(Context context) {
        inflater=LayoutInflater.from(context);
    }

    public void setData(ArrayList<VeTau> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(FaceItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VeTauHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=inflater.inflate(R.layout.item_layout,viewGroup,false);
        VeTauHolder holder=new VeTauHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VeTauHolder veTauHolder, final int i) {
        final VeTau item=data.get(i);
        veTauHolder.bindData(item);
        veTauHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(i);

                }
            }
        });
        veTauHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener != null){
                    listener.onLongClick(i,v);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null ? 0: data.size();
    }

    public class VeTauHolder extends RecyclerView.ViewHolder {
        private TextView GaDi;
        private TextView GaDen;
        private TextView Khuhoi;
        private TextView DonGia;
        public VeTauHolder(@NonNull View itemView) {
            super(itemView);
            GaDi=itemView.findViewById(R.id.tv_gadi);
            GaDen=itemView.findViewById(R.id.tv_gaden);
            Khuhoi=itemView.findViewById(R.id.tv_khuhoi);
            DonGia=itemView.findViewById(R.id.tv_DonGia);
        }

        public void bindData(VeTau item) {
            GaDi.setText(item.getGaDi());
            GaDen.setText(item.getGaDen());

            boolean kh=item.isKhuhoi();
            String kh1 = null;
            double tongtien = 0;
            if(kh==true){
                kh1="Khứ hồi";
                tongtien=item.getDonGia()*2*0.95;
            }
            if(kh==false){
                kh1="Một chiều";
                tongtien=item.getDonGia();
            }

            Khuhoi.setText(kh1);
            String tongtien1=String.valueOf(tongtien);

            DonGia.setText(tongtien1);
        }
    }

    public interface FaceItemListener{
        void onClick(int position);
        void onLongClick(int position, View v);
    }
}
