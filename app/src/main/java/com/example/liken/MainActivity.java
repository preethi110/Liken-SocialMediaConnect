package com.example.liken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username,instagram,facebook,twitter,spotify,whatsapp,snapchat,linkedin;
    Button insert,view,update, delete;
    com.example.liken.DBHelper DB;
    SharedPreferences sharedPreferences;

    private  static  final  String SHARED_PREF_NAME = "mypref";
    private  static  final String KEY_NAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        spotify = findViewById(R.id.spotify);
        whatsapp = findViewById(R.id.whatsapp);
        snapchat = findViewById(R.id.snapchat);
        linkedin = findViewById(R.id.linkedin);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        DB = new  com.example.liken.DBHelper(this);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
         String name = sharedPreferences.getString(KEY_NAME,null);

        if (name!=null){
            Intent intent = new Intent(MainActivity.this,Homeactivity.class);
            startActivity(intent);
        }

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, username.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Homeactivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "registratrion success", Toast.LENGTH_SHORT).show();

                String usernameTXT = username.getText().toString();
                String instagramTXT = "https://www.instagram.com/"+instagram.getText().toString();
                String facebookTXT = "https://www.facebook.com/"+facebook.getText().toString();
                String twitterTXT = "https://twitter.com/"+twitter.getText().toString();
                String spotifyTXT = "https://www.spotify.com/"+spotify.getText().toString();
                String whatsappTXT = "https://wa.me/"+whatsapp.getText().toString();
                String snapchatTXT = "https://www.snapchat.com/"+snapchat.getText().toString();
                String linkedinTXT = "https://www.linkedin.com/"+linkedin.getText().toString();
                String err = "Field Should not be empty!";
                if (usernameTXT.isEmpty()) username.setError(err);
                else if (instagramTXT.isEmpty()) instagram.setError(err);
                else if (facebookTXT.isEmpty()) facebook.setError(err);
                else if (twitterTXT.isEmpty()) twitter.setError(err);
                else if (spotifyTXT.isEmpty()) spotify.setError(err);
                else if (whatsappTXT.isEmpty()) whatsapp.setError(err);
                else if (snapchatTXT.isEmpty()) snapchat.setError(err);
                else if (linkedinTXT.isEmpty()) snapchat.setError(err);
                else {
                    Boolean checkinsertdata = DB.insertuserdata(usernameTXT, instagramTXT, facebookTXT, twitterTXT, spotifyTXT, whatsappTXT, snapchatTXT, linkedinTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(MainActivity.this, "registratrion success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Homeactivity.class));
                    } else
                        Toast.makeText(MainActivity.this, "Please Enter valid Data.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Username:" + res.getString(0) + "\n");
                    buffer.append("Instagram:" + res.getString(1) + "\n");
                    buffer.append("Facebook :" + res.getString(2) + "\n");
                    buffer.append("Twitter:" + res.getString(3) + "\n");
                    buffer.append("Spotify" + res.getString(4) + "\n");
                    buffer.append("Whatsapp:" + res.getString(5) + "\n");
                    buffer.append("Snapchat" + res.getString(6) + "\n");
                    buffer.append("Linkedin:" + res.getString(7) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameTXT = username.getText().toString();
                String instagramTXT = instagram.getText().toString();
                String facebookTXT = facebook.getText().toString();
                String twitterTXT = twitter.getText().toString();
                String spotifyTXT = spotify.getText().toString();
                String whatsappTXT = whatsapp.getText().toString();
                String snapchatTXT = snapchat.getText().toString();
                String linkedinTXT = linkedin.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(usernameTXT, instagramTXT, facebookTXT, twitterTXT, spotifyTXT, whatsappTXT, snapchatTXT,linkedinTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT =username.getText().toString();
                Boolean checkudeletedata = DB.deletedata(usernameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });


    }
    public void onLoginClick(View view) {

    }

    public void gotohome(View view) {

        startActivity(new Intent(this, Homeactivity.class));
    }
}