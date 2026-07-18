package com.kugaonkarp.covid19;

public class CovidModel {
    private String country;
    private long confirmed;
    private long deaths;
    private long recovered;
    private long active;

    public CovidModel(String country, long confirmed, long deaths, long recovered, long active) {
        this.country = country;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public String getCountry() { return country; }
    public long getConfirmed() { return confirmed; }
    public long getDeaths() { return deaths; }
    public long getRecovered() { return recovered; }
    public long getActive() { return active; }
}
