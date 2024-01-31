package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news_app.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        btnlogin = (Button) findViewById(R.id.btnloginLogin);
        db = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrnm = username.getText().toString();
                String pass = password.getText().toString();
                
                if(usrnm.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Popunite sva polja!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = db.checkUser(usrnm, pass);
                    String tipKorisnika = db.getTipKorisnika(usrnm, pass);
                    if(checkUser){
                        if(tipKorisnika.equals("korisnik")) {
                            Toast.makeText(LoginActivity.this, "Uspesno ste ulogovani!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                        else if(tipKorisnika.equals("admin")){
                            Toast.makeText(LoginActivity.this, "Uspesno ste ulogovani!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Nevazeci podaci!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}