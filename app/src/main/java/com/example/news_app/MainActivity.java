package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news_app.Model.Korisnik;


public class MainActivity extends AppCompatActivity {

    EditText ime, prezime, username, password, repassword;
    Button login, register;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ime = (EditText) findViewById(R.id.ime);
        prezime = (EditText) findViewById(R.id.prezime);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        login = (Button) findViewById(R.id.btnlogin);
        register = (Button) findViewById(R.id.btnregister);
        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newime = ime.getText().toString();
                String newpreime = prezime.getText().toString();
                String usrnm = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                
                if(newime.equals("") || newpreime.equals("") || usrnm.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this, "Polja ne smeju biti prazna!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkUser = db.checkUsername(usrnm);
                        if(!checkUser){
                            Korisnik k = new Korisnik(0, newime, newpreime, usrnm, pass, "korisnik");
                            Boolean registrujKorisnika = db.dodajKorisnika(k);
                            if(registrujKorisnika){
                                Toast.makeText(MainActivity.this, "Korisnik uspesno registrovan!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Registracija neuspesna!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Korisnik vec postoji!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Lozinke se ne poklapaju!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}