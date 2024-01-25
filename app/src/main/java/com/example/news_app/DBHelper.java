package com.example.news_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.news_app.Model.Korisnik;
import com.example.news_app.Model.TipKorisnika;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
        mydb.execSQL("drop Table if exists korisnik");
    }

    public Boolean dodajKorisnika(Korisnik korisnik){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", korisnik.getId());
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
    public Boolean insertData(String username, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = mydb.insert("korisnik", null, contentValues);
        if(result==-1) return false;
        else return true;
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
