package com.example.doan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BaiXeAdminAdapter extends RecyclerView.Adapter<BaiXeAdminViewHolder> {
    private Context context;
    private List<BaiXe> baiXeList;
    public BaiXeAdminAdapter(Context context, List<BaiXe> baiXeList){
        this.context = context;
        this.baiXeList = baiXeList;
    }
    @NonNull
    @Override
    public BaiXeAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.baixe_item, parent, false);
        return new BaiXeAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiXeAdminViewHolder holder, int position) {
        Glide.with(context).load(baiXeList.get(position).getHinhAnh()).into(holder.recImage);
        holder.recTenBai.setText(baiXeList.get(position).getTenBaiXe());
        //
        float floatValue = Float.parseFloat(String.valueOf(baiXeList.get(position).getGia()));
        String formattedFloatValue = String.format("%.0f", floatValue);
        String result = formattedFloatValue+ " vnđ/giờ";
        holder.recGia.setText(result);
        //
        holder.recTrangThai.setText(baiXeList.get(position).getTrangThai());

        holder.recBaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailADActivity.class);
                intent.putExtra("hinhAnh", baiXeList.get(holder.getAdapterPosition()).getHinhAnh());
                intent.putExtra("tenBaiXe", baiXeList.get(holder.getAdapterPosition()).getTenBaiXe());
                intent.putExtra("diaChi",baiXeList.get(holder.getAdapterPosition()).getDiaChi());
                intent.putExtra("gia", baiXeList.get(holder.getAdapterPosition()).getGia());
                intent.putExtra("soChoDo", baiXeList.get(holder.getAdapterPosition()).getSoChoDo());
                intent.putExtra("trangThai", baiXeList.get(holder.getAdapterPosition()).getTrangThai());
                intent.putExtra("key",baiXeList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return baiXeList.size();
    }
}

class BaiXeAdminViewHolder extends RecyclerView.ViewHolder{
    CardView recBaiXe;
    ImageView recImage;
    TextView recTenBai,recTrangThai,recGia;
    public BaiXeAdminViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recBaiXe = itemView.findViewById(R.id.recBaiXe);
        recTenBai = itemView.findViewById(R.id.recTenBai);
        recTrangThai = itemView.findViewById(R.id.recTrangThai);
        recGia = itemView.findViewById(R.id.recGia);
    }
}