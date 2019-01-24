package com.example.inteli5.recilerroomanimation;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

class SeguroActivity extends AppCompatActivity {

    Button seguro, cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguro_activyti);

        seguro = findViewById(R.id.btn_borrado);
        cancelar = findViewById(R.id.btn_cancelar);

        int d = getIntent().getIntExtra("ola",-1);
        int id = 0;
        if (d == -1) {
            Toast.makeText(this, "hay registros", Toast.LENGTH_SHORT).show();
        }else{
            id = d;
        }

        final int finalId = id;
        seguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"ola soy smanc",Snackbar.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AllData allData = AllData.getInstance(getApplicationContext());
                        allData.fechaValorDao().deleteFechaValor(finalId);
                    }
                }).start();
                startActivity(new Intent(SeguroActivity.this,MainActivity.class));
                finish();
            }

        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeguroActivity.this,MainActivity.class));
            }
        });



    }
}
