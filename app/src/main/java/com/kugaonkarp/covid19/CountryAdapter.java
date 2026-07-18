package com.kugaonkarp.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final Context context;
    private final List<CovidModel> countryList;

    public CountryAdapter(Context context, List<CovidModel> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidModel model = countryList.get(position);
        holder.tvCountry.setText(model.getCountry());
        holder.tvConfirmed.setText("Confirmed: " + model.getConfirmed());
        holder.tvDeaths.setText("Deaths: " + model.getDeaths());
        holder.tvRecovered.setText("Recovered: " + model.getRecovered());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry, tvConfirmed, tvDeaths, tvRecovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvConfirmed = itemView.findViewById(R.id.tv_confirmed);
            tvDeaths = itemView.findViewById(R.id.tv_deaths);
            tvRecovered = itemView.findViewById(R.id.tv_recovered);
        }
    }
}
