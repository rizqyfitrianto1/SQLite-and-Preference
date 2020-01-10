package com.example.trainer.androidlatihanlima;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainer.androidlatihanlima.helpers.PrefsHelper;

public class EditNamaClass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_nama);

        final EditText et_nama = findViewById(R.id.et_nama);
        Button btnedit = findViewById(R.id.btn_edit);


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_nama.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Nama Kosong",
                            Toast.LENGTH_SHORT).show();

                }else{
                    PrefsHelper.sharedInstance(getApplicationContext())
                            .setNamaDefault(et_nama.getText().toString());
                    startActivity(new Intent(EditNamaClass.this, NamaClass.class));
                    finish();
                }
            }
        });

    }
}
