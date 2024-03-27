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
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.doan.ui.gallery.GalleryADFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditActivity extends AppCompatActivity {
    ImageView editImage;
    Button editSave;
    EditText editTenBai, editDiaChi, editSoChoDo, editGia, editTrangThai;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbaixe);
        editSave = findViewById(R.id.editSave);
        editTenBai = findViewById(R.id.editTenBai);
        editDiaChi = findViewById(R.id.editDiaChi);
        editSoChoDo = findViewById(R.id.editSoChoDo);
        editGia = findViewById(R.id.editGia);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            editImage.setImageURI(uri);
                        } else {
                            Toast.makeText(EditActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(EditActivity.this).load(bundle.getString("hinhAnh")).into(editImage);
            editTenBai.setText(bundle.getString("tenBaiXe"));
            editDiaChi.setText(bundle.getString("diaChi"));
            float defaultValue = 0.0f; // giá trị mặc định được truyền vào
            float gia = bundle.getFloat("gia",defaultValue);
            String giaFloatValue = String.format("%.0f",gia);
            editGia.setText(String.valueOf(giaFloatValue));
            float soChoDo = bundle.getFloat("soChoDo", defaultValue);
            String soFloatValue = String.format("%.0f",soChoDo);
            editSoChoDo.setText(String.valueOf(soFloatValue));
            key = bundle.getString("key");
            oldImageURL = bundle.getString("hinhAnh");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("BaiXe").child(key);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        editSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(EditActivity.this, GalleryADFragment.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                updateData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void updateData(){
        String tenBai = editTenBai.getText().toString().trim();
        String diaChi = editDiaChi.getText().toString().trim();
        Float gia  = parseFloat(editGia.getText().toString());
        Float soChoDo = parseFloat(editSoChoDo.getText().toString());
        String trangThai = "Còn Trống";
        BaiXe baiXe = new BaiXe(tenBai, diaChi, imageUrl, soChoDo,trangThai,gia);
        databaseReference.setValue(baiXe).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(EditActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
