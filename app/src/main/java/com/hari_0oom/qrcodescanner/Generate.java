package com.hari_0oom.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Generate extends AppCompatActivity {

    Button btn, share;
    ImageView img;
    EditText field;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        btn=findViewById(R.id.generatebtn);
        img=findViewById(R.id.result);
        field=findViewById(R.id.field);
        share=findViewById(R.id.share);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCode();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Generate.this, "Sharing", Toast.LENGTH_SHORT).show();


                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select an application"));

            }
        });

    }

    private void generateCode() {
        String content=field.getText().toString().trim();
        BarcodeEncoder encoder= new BarcodeEncoder();
        try{
            bitmap= encoder.encodeBitmap(content, BarcodeFormat.QR_CODE,400, 400);
            img.setImageBitmap(bitmap);
            img.setVisibility(View.VISIBLE);
            share.setEnabled(true);
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}