package com.example.liken;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Scanactivity extends AppCompatActivity {
    Button btScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanactivity);


        btScan = findViewById(R.id.bt_scan);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(Scanactivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setCaptureActivity(CaptureActivityPortrait.class);
                integrator.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );
        if(intentResult.getContents() != null){
            Toast.makeText(getApplicationContext(),"Captured",Toast.LENGTH_SHORT).show();
            gotoUrl(intentResult.getContents());
            //gotoUrl(intentResult.getContents()+"");
        }

        else{
            Toast.makeText(getApplicationContext(),"OOPS... you did not scan anything",Toast.LENGTH_SHORT).show();

        }
    }

    private void gotoUrl(String s) {
        try{
            Uri uri = Uri.parse(s);
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }catch (Exception error){
            Toast.makeText(getApplicationContext(),"Inavlid URl",Toast.LENGTH_SHORT).show();
        }
    }

    public void onLoginleftscanClick(View view) {
        startActivity(new Intent(this, Homeactivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);

    }
}