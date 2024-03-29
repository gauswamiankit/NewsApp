package com.example.newsapp.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.Adapter;
import com.example.newsapp.ApiClient;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.Model.Headlines;
import com.example.newsapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technology extends AppCompatActivity {

    TextView wish,date;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    final String API_KEY = "ceb1a1b595284b9d993af9152f51c202";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    private String category="technology";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);


        date = findViewById(R.id.date);

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        date.setText(dateTime);


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

        Call<Headlines> call = ApiClient.getInstance().getApi().getCategoryNews(country,category,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(Technology.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
//                Toast.makeText(Feed.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                Toast.makeText(Technology.this, "This is my Toast message!",
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