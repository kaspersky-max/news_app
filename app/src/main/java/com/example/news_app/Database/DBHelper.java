package com.example.news_app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.Vest;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "NewsApp.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table korisnik(id INTEGER primary key autoincrement, ime TEXT, prezime TEXT, username TEXT unique not null, password TEXT not null, tip_korisnika TEXT not null)");
        mydb.execSQL("create Table vest(id INTEGER primary key autoincrement, autor TEXT, naslov TEXT, sadrzaj TEXT, url TEXT, urlToImage TEXT, datum TEXT)");

        String ime = "Admin";
        String prezime = "Admin";
        String username = "admin";
        String password = "admin123";
        String tipKorisnika = "admin";

        String insertStatement = "INSERT INTO korisnik (ime, prezime, username, password, tip_korisnika) VALUES (?, ?, ?, ?, ?)";

        mydb.execSQL(insertStatement, new String[]{ime, prezime, username, password, tipKorisnika});

    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
        mydb.execSQL("drop Table if exists korisnik");
    }

    public Boolean dodajKorisnika(Korisnik korisnik){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ime", korisnik.getIme());
        contentValues.put("prezime", korisnik.getPrezime());
        contentValues.put("username", korisnik.getUsername());
        contentValues.put("password", korisnik.getPassword());
        contentValues.put("tip_korisnika", korisnik.getTipKorisnika());
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.insert("korisnik", null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public void izmeniKorisnika(Korisnik korisnik){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ime", korisnik.getIme());
        contentValues.put("prezime", korisnik.getPrezime());
        contentValues.put("username", korisnik.getUsername());
        contentValues.put("password", korisnik.getPassword());
        contentValues.put("tip_korisnika", korisnik.getTipKorisnika());
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.update("korisnik", contentValues, "id=?", new String[]{String.valueOf(korisnik.getId())});
    }

    public Boolean dodajVest(Vest vest){
        ContentValues contentValues = new ContentValues();
        contentValues.put("autor", vest.getAutor());
        contentValues.put("naslov", vest.getNaslov());
        contentValues.put("sadrzaj", vest.getSadrzaj());
        contentValues.put("url", vest.getUrl());
        contentValues.put("urlToImage", vest.getUrlToImage());
        contentValues.put("datum", vest.getDatumPostavljanja());
        SQLiteDatabase mydb = this.getWritableDatabase();
        long result = mydb.insert("vest", null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public void izmeniVest(Vest vest){
        ContentValues contentValues = new ContentValues();
        contentValues.put("autor", vest.getAutor());
        contentValues.put("naslov", vest.getNaslov());
        contentValues.put("sadrzaj", vest.getSadrzaj());
        contentValues.put("url", vest.getUrl());
        contentValues.put("urlToImage", vest.getUrlToImage());
        contentValues.put("datum", vest.getDatumPostavljanja());
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.update("vest", contentValues, "id=?", new String[]{String.valueOf(vest.getId())});
    }

    public void obrisiKorisnika(int id){
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.delete("korisnik", "id=?", new String[]{String.valueOf(id)});
    }
    public void obrisiVest(int id){
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.delete("vest", "id=?", new String[]{String.valueOf(id)});
    }
    public List<Korisnik> getKorisnikLista(){
        String sql = "select * from korisnik";
        SQLiteDatabase mydb = this.getReadableDatabase();
        List<Korisnik> dodajKorisnike = new ArrayList<>();
        Cursor cursor = mydb.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String ime = cursor.getString(1);
                String prezime = cursor.getString(2);
                String username = cursor.getString(3);
                String password = cursor.getString(4);
                String tip_korisnika = cursor.getString(5);
                dodajKorisnike.add(new Korisnik(id,ime, prezime, username, password, tip_korisnika));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dodajKorisnike;
    }

    public List<Vest> getVestLista(){
        String sql = "select * from vest";
        SQLiteDatabase mydb = this.getReadableDatabase();
        List<Vest> dodajVesti = new ArrayList<>();
        Cursor cursor = mydb.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String autor = cursor.getString(1);
                String naslov = cursor.getString(2);
                String sadrzaj = cursor.getString(3);
                String url = cursor.getString(4);
                String urlToImage = cursor.getString(5);
                String datum = cursor.getString(6);
                dodajVesti.add(new Vest(id,autor, naslov, sadrzaj, url, urlToImage, datum));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dodajVesti;
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = mydb.insert("korisnik", null, contentValues);
        if(result==-1) return false;
        else return true;
    }

    public String getTipKorisnika(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select tip_korisnika from korisnik where username=? and password=?", new String[]{username, password});
        int tipKorisnikaIndex = cursor.getColumnIndex("tip_korisnika");
        if (cursor.moveToFirst()) {
            String tipKorisnika = cursor.getString(tipKorisnikaIndex);
            return tipKorisnika;
        } else {
            return null;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from korisnik where username=?", new String[]{username});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUser(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("Select * from korisnik where username=? and password=?", new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
