package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VeAdapter extends RecyclerView.Adapter<VeViewHolder>{
    private Context context;
    private List<Ve> veList;
    public VeAdapter(Context context, List<Ve> veList){
        this.context = context;
        this.veList = veList;
    }
    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ve_item, parent, false);
        return new VeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        holder.recTenNguoiDat.setText(veList.get(position).getTenNguoiDat());
        float floatValue = Float.parseFloat(String.valueOf(veList.get(position).getTongTien()));
        String formattedFloatValue = String.format("%.0f", floatValue);
        holder.recGiaVe.setText(formattedFloatValue);
//        holder.recGiaVe.setText(veList.get(position).getTongTien());
        holder.recTenBaiXe.setText(veList.get(position).getTenBaiXe());

    }

    @Override
    public int getItemCount() {
        return veList.size();
    }
}

class VeViewHolder extends RecyclerView.ViewHolder{
    CardView recVe;
    ImageView recImage;
    TextView recTenBaiXe,recTenNguoiDat,recGiaVe;
    public VeViewHolder(@NonNull View itemView) {
        super(itemView);
//        recImage = itemView.findViewById(R.id.recImage);
        recVe = itemView.findViewById(R.id.recBaiXe);
        recTenNguoiDat = itemView.findViewById(R.id.recTenNguoiDat);
        recGiaVe = itemView.findViewById(R.id.recGiaVe);
        recTenBaiXe = itemView.findViewById(R.id.recTenBaiXe);
    }
}
