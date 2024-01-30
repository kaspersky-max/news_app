package com.example.news_app.Model;

import java.util.ArrayList;

public class Response {
    private String status;
    private String totalResults;
    private ArrayList<Vest> articles;

    public Response(String status, String totalResults, ArrayList<Vest> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Vest> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Vest> articles) {
        this.articles = articles;
    }
}
