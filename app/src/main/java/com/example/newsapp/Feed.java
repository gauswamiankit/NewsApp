package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newsapp.Category.Entertainment;
import com.example.newsapp.Category.Health;
import com.example.newsapp.Category.Science;
import com.example.newsapp.Category.Sports;
import com.example.newsapp.Category.Technology;
import com.example.newsapp.Model.Articles;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Feed extends AppCompatActivity {

    TextView wish,date;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText etQuery;
    Button btnSearch;
    final String API_KEY = "ceb1a1b595284b9d993af9152f51c202";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    private String category="technology";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.feed);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.feed:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
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


        CardView cardView = findViewById(R.id.cardSports);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feed.this,Sports.class));
            }
        });
        CardView cardView1 = findViewById(R.id.cardHealth);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feed.this, Health.class));
            }
        });
        CardView cardView2 = findViewById(R.id.cardScience);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feed.this, Science.class));
            }
        });
        CardView cardView3 = findViewById(R.id.cardEntertainment);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feed.this, Entertainment.class));
            }
        });
        CardView cardView4 = findViewById(R.id.cardTechnology);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feed.this, Technology.class));
            }
        });


//        date = findViewById(R.id.date);
//
//        Calendar calendar = Calendar.getInstance();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
//        String dateTime = simpleDateFormat.format(calendar.getTime());
//
//
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
    }

//    public void retriveJson(String query ,String country, String apiKey){
//
//        swipeRefreshLayout.setRefreshing(true);
//
//            Call<Headlines>  call = ApiClient.getInstance().getApi().getCategoryNews(country,category,apiKey);
//            call.enqueue(new Callback<Headlines>() {
//            @Override
//            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
//                if (response.isSuccessful() && response.body().getArticles() != null){
//                    swipeRefreshLayout.setRefreshing(false);
//                    articles.clear();
//                    articles = response.body().getArticles();
//                    adapter = new Adapter(Feed.this,articles);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Headlines> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
////                Toast.makeText(Feed.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
//                Toast.makeText(Feed.this, "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//    public String getCountry(){
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
//        return country.toLowerCase();
    }

