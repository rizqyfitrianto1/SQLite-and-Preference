package com.example.trainer.androidlatihanlima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainer.androidlatihanlima.helpers.PrefsHelper;

public class LoginActivity extends AppCompatActivity {
    EditText et_nama,et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_nama = findViewById(R.id.et_nama);
        et_password = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);

        boolean ceklogin = PrefsHelper.sharedInstance(getApplicationContext()).isUserLogin();
        if(ceklogin){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_nama.getText().toString().equals("")|| et_password.getText().toString().equals(" ")){
                    Toast.makeText(LoginActivity.this, "Password dan Username Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    if (et_nama.getText().toString().equals("rezky")&& et_password.getText().toString().equals("rezky123")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        PrefsHelper.sharedInstance(getApplicationContext()).cekLogin(true);
                        PrefsHelper.sharedInstance(getApplicationContext()).setNamaDefault("Admin");
                        finish();
                    }else if (et_nama.getText().toString().equals("user")&& et_password.getText().toString().equals("user")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        PrefsHelper.sharedInstance(getApplicationContext()).cekLogin(true);
                        PrefsHelper.sharedInstance(getApplicationContext()).setNamaDefault("Pengguna");
                        finish();
                    }
                }

            }
        });

    }
}
