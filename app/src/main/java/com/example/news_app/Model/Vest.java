package com.example.news_app.Model;

public class Vest {
    int id;
    private String autor, naslov, sadrzaj, url, urlToImage, datumPostavljanja;

    public Vest(int id, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.id = id;
        this.autor = author;
        this.naslov = title;
        this.sadrzaj = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.datumPostavljanja = publishedAt;
    }

    public int getId(){return id; }
    public void setId(int id){ this.id = id; }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDatumPostavljanja() {
        return datumPostavljanja;
    }

    public void setDatumPostavljanja(String datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }
}
