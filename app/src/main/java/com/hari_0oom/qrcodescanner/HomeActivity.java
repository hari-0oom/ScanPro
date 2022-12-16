package com.hari_0oom.qrcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity {

    Button scan, generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        scan =findViewById(R.id.buttonScan);
        generate =findViewById(R.id.buttonGenerate);


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Generate.class);
                startActivity(intent);
            }
        });
    }

    private void scanCode() {
        Toast.makeText(this, "scanning", Toast.LENGTH_SHORT).show();
        IntentIntegrator intentIntegrator=new IntentIntegrator(this );
        intentIntegrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result==null){
            super.onActivityResult(requestCode, resultCode, data);
        }else{
            String r = result.getContents();
            if(r==null){

            }else{
                showBox(r);
            }
        }
    }

    private void showBox(String r) {
        AlertDialog.Builder bl= new AlertDialog.Builder(this);
        bl.setTitle("Scanned Result");
        bl.setMessage(r);
        bl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        bl.create().show();
    }
}