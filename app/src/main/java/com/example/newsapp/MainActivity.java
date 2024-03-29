package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.Category.Technology;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.Model.Headlines;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView wish,date;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText etQuery;
    Button btnSearch;
    final String API_KEY = "ceb1a1b595284b9d993af9152f51c202";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        return true;
                    case R.id.app:
                        startActivity(new Intent(getApplicationContext()
                                ,App.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        date = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        date.setText(dateTime);

//        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
//        recyclerView = findViewById(R.id.recyclerView);

//        etQuery = findViewById(R.id.etQuery);
//        btnSearch = findViewById(R.id.btnSearch);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        String country = getCountry();
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                retriveJson("",country,API_KEY);
//            }
//        });
//
//        retriveJson("",country,API_KEY);
//
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!etQuery.getText().toString().equals("")){
//
//                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                        @Override
//                        public void onRefresh() {
//                            retriveJson(etQuery.getText().toString(),country,API_KEY);
//                        }
//                    });
//
//                    retriveJson(etQuery.getText().toString(),country,API_KEY);
//                }else {
//                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                        @Override
//                        public void onRefresh() {
//                            retriveJson("",country,API_KEY);
//                        }
//                    });
//
//                    retriveJson("",country,API_KEY);
//                }
//            }
//        });
//    }
//
//    public void retriveJson(String query ,String country, String apiKey){
//
//        swipeRefreshLayout.setRefreshing(true);
//        Call<Headlines> call;
//        if (!etQuery.getText().toString().equals("")){
//            call = ApiClient.getInstance().getApi().getSpecificData(country,apiKey);
//
//        }else{
//            call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
//        }
//        call.enqueue(new Callback<Headlines>() {
//            @Override
//            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
//                if (response.isSuccessful() && response.body().getArticles() != null){
//                    swipeRefreshLayout.setRefreshing(false);
//                    articles.clear();
//                    articles = response.body().getArticles();
//                    adapter = new Adapter(MainActivity.this,articles);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Headlines> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
//                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    public String getCountry(){
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
//        return country.toLowerCase();

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCountry();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retriveJson("",country,API_KEY);
            }
        });

        retriveJson("",country,API_KEY);
    }

    public void retriveJson(String query ,String country, String apiKey){

        swipeRefreshLayout.setRefreshing(true);

        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
//                Toast.makeText(Feed.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Coudn't Feed Refresh",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();


    }

}