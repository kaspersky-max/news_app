package com.example.news_app.Model;

public class Korisnik {

    private int id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String tip_korisnika;

    public Korisnik(int id, String ime, String prezime, String username, String password, String tip_korisnika) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.tip_korisnika = tip_korisnika;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipKorisnika() {
        return tip_korisnika;
    }

    public void setTipKorisnika(String tip_korisnika) {
        this.tip_korisnika = tip_korisnika;
    }
}
