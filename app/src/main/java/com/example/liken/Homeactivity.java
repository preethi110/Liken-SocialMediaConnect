package com.example.liken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Homeactivity extends AppCompatActivity {

    TextView textname;


    SharedPreferences sharedPreferences;
    private  static  final  String SHARED_PREF_NAME = "mypref";
    private  static  final String KEY_NAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        DBHelper db = new DBHelper(this);
        Cursor res = db.getdata();


        textname = findViewById(R.id.text_fullname);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if(name !=null){
            textname.setText("USER  :  "+name);

        }
    }

    public void  showQrCode(){

        startActivity(new Intent(Homeactivity.this,Qractivity.class));
    }

    public void gotoinsta(View view) {
        new Media().current_data = 1;
        showQrCode();
    }

    public void gotoface(View view){
        new Media().current_data = 2;
        showQrCode();
    }

    public void gototweet(View view){
        new Media().current_data = 3;
        showQrCode();
    }

    public void gotospot(View view){
        new Media().current_data = 4;
        showQrCode();
    }

    public void gotowhats(View view){
        new Media().current_data = 5;
        showQrCode();
    }

    public void gotosnap(View view){
        new Media().current_data = 6;
        showQrCode();
    }

    public void gotolinkin(View view){
        new Media().current_data = 7;
        showQrCode();
    }


    public void onLoginrightClick(View view) {
        startActivity(new Intent(this, Scanactivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}