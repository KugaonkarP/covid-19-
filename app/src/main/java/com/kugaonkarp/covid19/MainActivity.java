package com.kugaonkarp.covid19;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvGlobalConfirmed, tvGlobalDeaths, tvGlobalRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        tvGlobalConfirmed = findViewById(R.id.tv_global_confirmed);
        tvGlobalDeaths = findViewById(R.id.tv_global_deaths);
        tvGlobalRecovered = findViewById(R.id.tv_global_recovered);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchGlobalData();
        fetchCountriesData();
    }

    private void fetchGlobalData() {
        RetrofitClient.getInstance().getApi().getGlobalData().enqueue(new Callback<CovidModel>() {
            @Override
            public void onResponse(Call<CovidModel> call, Response<CovidModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CovidModel data = response.body();
                    tvGlobalConfirmed.setText("Confirmed: " + data.getConfirmed());
                    tvGlobalDeaths.setText("Deaths: " + data.getDeaths());
                    tvGlobalRecovered.setText("Recovered: " + data.getRecovered());
                }
            }

            @Override
            public void onFailure(Call<CovidModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch global data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchCountriesData() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getInstance().getApi().getCountriesData().enqueue(new Callback<List<CovidModel>>() {
            @Override
            public void onResponse(Call<List<CovidModel>> call, Response<List<CovidModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new CountryAdapter(MainActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CovidModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
