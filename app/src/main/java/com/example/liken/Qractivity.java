package com.example.liken;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class    Qractivity extends AppCompatActivity {
    // variables for imageview, edittext,
    // button, bitmap and qrencoder.
    private ImageView qrCodeIV;
    String username;
    int cdata;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);

        Button clos = findViewById(R.id.close);
        ImageView iconic = (ImageView) findViewById(R.id.iconi);
        TextView textti = (TextView) findViewById(R.id.titext);
        TextView usern = (TextView) findViewById(R.id.user_name);
        cdata = new Media().current_data;


        Cursor res = DB.getdata();
        while (res.moveToNext()) {
            username = res.getString(new Media().current_data);
            break;
        }

        // initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode);

        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(username, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            qrCodeIV.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

        if(cdata == 1){
            iconic.setImageResource(R.drawable.insta);
            textti.setText("Instagram");
            usern.setText(username);
        }
        else if(cdata == 2){
            iconic.setImageResource(R.drawable.facebook);
            textti.setText("Facebook");
            usern.setText(username);
        }
        else if(cdata == 3){
            iconic.setImageResource(R.drawable.twi);
            textti.setText("Twitter");
            usern.setText(username);
        }
        else if(cdata == 4){
            iconic.setImageResource(R.drawable.spotify);
            textti.setText("Spotify");
            usern.setText(username);
        }
        else if(cdata == 5){
            iconic.setImageResource(R.drawable.whatsapp);
            textti.setText("Whatsapp");
            usern.setText(username);
        }
        else if(cdata == 6){
            iconic.setImageResource(R.drawable.snappp);
            textti.setText("Snapchat");
            usern.setText(username);
        }
        else if(cdata == 7){
            iconic.setImageResource(R.drawable.linkedin1);
            textti.setText("Linkedin");
            usern.setText(username);
        } else {
            Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();
        }

        clos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
