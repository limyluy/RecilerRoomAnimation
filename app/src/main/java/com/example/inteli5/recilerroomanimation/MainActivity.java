package com.example.inteli5.recilerroomanimation;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private FechaValorViewModel fechaValorViewModel;
    public static String fecha;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        CargarFechaHoy();
        llenarRecycler();


    }

    private void llenarRecycler() {
        rcv = findViewById(R.id.rcv_main);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        final FechaAdaptador adaptador = new FechaAdaptador(getApplicationContext());
        rcv.setAdapter(adaptador);


        fechaValorViewModel = ViewModelProviders.of(this).get(FechaValorViewModel.class);
        fechaValorViewModel.getFechaVAlor().observe(this, new Observer<List<FechaValor>>() {
            @Override
            public void onChanged(@Nullable List<FechaValor> fechaValors) {
                adaptador.setFechaValors(fechaValors);
            }
        });

        adaptador.setOnIteMClickListener(new FechaAdaptador.OnItemClickListener() {

            @Override
            public void onItemClick(final FechaValor fechaValor) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                Intent i = new Intent(MainActivity.this, SeguroActivity.class);
                i.putExtra("ola",fechaValor.getId_fechavalor());
                startActivity(i);



            }
        });
    }

    private void CargarFechaHoy() {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        Date a = c.getTime();
        SimpleDateFormat ft = new SimpleDateFormat("EE dd MMM yyyy");
        String d = String.valueOf(ft.format(a));
        fecha = d;
        Log.e("fecha", d);
    }
}
