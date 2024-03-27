package com.example.doan;

import static java.lang.Float.parseFloat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.clans.fab.FloatingActionButton;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DetailADActivity  extends AppCompatActivity {
    ImageView detailImage;
    TextView detailTenBai, detailDiaChi, detailSoChoDo, detailGia, detailTrangThai;
    FloatingActionButton editButton;
    String imageUrl,key;
    Uri uri;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailadmin);
        detailImage = findViewById(R.id.detailImage);
        detailTenBai = findViewById(R.id.detailTenBai);
        detailDiaChi = findViewById(R.id.detailDiaChi);
        detailSoChoDo = findViewById(R.id.detailSoChoDo);
        detailGia = findViewById(R.id.detailGia);
        detailTrangThai = findViewById(R.id.detailTrangThai);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            float defaultValue = 0.0f; // giá trị mặc định được truyền vào
            detailTenBai.setText("Tên bãi xe: " + bundle.getString("tenBaiXe"));
            detailDiaChi.setText("Địa chỉ: "+ bundle.getString("diaChi"));
            float gia = bundle.getFloat("gia",defaultValue);
            String giaFloatValue = String.format("%.0f",gia);
            detailGia.setText("Giá: "+String.valueOf(giaFloatValue) + " vnđ/1h");
            float soChoDo = bundle.getFloat("soChoDo", defaultValue);
            String soFloatValue = String.format("%.0f",soChoDo);
            detailSoChoDo.setText("Số chỗ đỗ: "+String.valueOf(soFloatValue));
            detailTrangThai.setText("Trạng thái: "+bundle.getString("trangThai"));
            key = bundle.getString("key");

            imageUrl = bundle.getString("hinhAnh");
            Glide.with(this).load(bundle.getString("hinhAnh")).into(detailImage);
        }


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailADActivity.this, EditActivity.class)
                        .putExtra("tenBaiXe", detailTenBai.getText().toString())
                        .putExtra("diaChi", detailDiaChi.getText().toString())
                        .putExtra("soChoDo", Float.parseFloat(detailSoChoDo.getText().toString()))
                        .putExtra("gia", Float.parseFloat(detailGia.getText().toString()))
                        .putExtra("trangThai", detailTrangThai.getText().toString())
                        .putExtra("hinhAnh", imageUrl)
                        .putExtra("key", key);
                startActivity(intent);
            }
        });
    }

}
