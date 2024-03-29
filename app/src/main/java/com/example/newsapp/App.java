package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import maes.tech.intentanim.CustomIntent;

public class App extends AppCompatActivity {

    View call,abhiCall,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.app);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.feed:
                        startActivity(new Intent(getApplicationContext()
                                , Feed.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.app:

                }
                return false;
            }
        });

//        call = findViewById(R.id.call);
//        abhiCall = findViewById(R.id.abhiCall);
        back = findViewById(R.id.backApp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                    CustomIntent.customType(App.this,"fadein-to-fadeout");            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(App.this,MainActivity.class);
//                startActivity(intent);
//
//                CustomIntent.customType(App.this,"fadein-to-fadeout");
//            }
//        });

//    @SuppressLint("CutPasteId") ImageView imageView = findViewById(R.id.backApp);
//        imageView.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        startActivity(new Intent(App.this, MainActivity.class));
//             }
//        });

//        call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoUrl("https://api.whatsapp.com/send?phone=917572914492&text=ANKIT");
//            }
//        });
//        abhiCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoUrl("https://api.whatsapp.com/send?phone=916352333023&text=ABHI");
//            }
//        });

//
//        startActivity(new Intent(App.this, MainActivity.class));
//        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
//
    }


//
//    private void gotoUrl(String s) {
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW,uri));
//    }

}

