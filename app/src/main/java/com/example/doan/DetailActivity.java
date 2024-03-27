package com.example.doan;

import static java.lang.Float.parseFloat;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView detailGia, detailTenBai, detailTrangThai,detailSoChoDo;
    ImageView detailImage;
    Button btnThanhToan;
    private float quantity = 1;
    private float total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datcho);

        detailGia = findViewById(R.id.tongtien);
        detailImage = findViewById(R.id.imgBaiXe);
        detailTenBai = findViewById(R.id.txtTenBaiXe);
        detailTrangThai = findViewById(R.id.txtTrangThai);
        detailSoChoDo = findViewById(R.id.txtSoChoDo);
        btnThanhToan = findViewById(R.id.btnthanhtoan);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            float defaultValue = 0.0f; // giá trị mặc định được truyền vào
            detailTrangThai.setText(bundle.getString("trangThai"));
            float gia = bundle.getFloat("gia",defaultValue);
            String giaFloatValue = String.format("%.0f",gia);
            detailGia.setText(String.valueOf(giaFloatValue));
            detailTenBai.setText(bundle.getString("tenBaiXe"));
            float soChoDo = bundle.getFloat("soChoDo", defaultValue);
            String soFloatValue = String.format("%.0f",soChoDo);
            detailSoChoDo.setText(String.valueOf(soFloatValue));
            Glide.with(this).load(bundle.getString("hinhAnh")).into(detailImage);
        }

        ImageView increaseImageView = findViewById(R.id.plus);
        increaseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float defaultValue = 0.0f;
                float gia = bundle.getFloat("gia",defaultValue);
                quantity++;
                total = quantity * gia;
                TextView quantityTextView = findViewById(R.id.soluong);
                String so = String.format("%.0f",quantity);
                quantityTextView.setText(so);
                TextView totalTextView = findViewById(R.id.tongtien);
                totalTextView.setText(String.format(Locale.getDefault(), "%.0f", total));
            }
        });

        ImageView decreaseImageView = findViewById(R.id.minus);
        decreaseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    float defaultValue = 0.0f;
                    float gia = bundle.getFloat("gia",defaultValue);
                    quantity--;
                    total = quantity * gia;
                    TextView quantityTextView = findViewById(R.id.soluong);
                    String so = String.format("%.0f",quantity);
                    quantityTextView.setText(so);
                    TextView totalTextView = findViewById(R.id.tongtien);
                    totalTextView.setText(String.format(Locale.getDefault(), "%.0f"+"đ", total));
                }
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ThanhToanActivity.class);
                intent.putExtra("tongTien",total );
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setCancelable(false);
        uploadData();
    }
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String gemail = user.getEmail();
    String gten = user.getDisplayName();
    String uid = user.getUid();
    DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document(uid);
    //        DocumentSnapshot doc = task.getResult();
//        String name = doc.getString("Name");
    public void uploadData(){
        String tenNguoiDat = gten;
        String emailNguoiDat = gemail;
        String tenBaiXe = detailTenBai.getText().toString();
        Float giaVe = parseFloat(detailGia.getText().toString());
        Float thoiGian = quantity;

        Ve ve = new Ve(tenNguoiDat, emailNguoiDat, tenBaiXe, thoiGian,giaVe);
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Ve").child(currentDate)
                .setValue(ve).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DetailActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
