package com.example.inteli5.recilerroomanimation;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity

public class FechaValor {
    @PrimaryKey(autoGenerate = true)
    private int id_fechavalor;

    private String fecha;

    private int valor;

    public FechaValor() {
    }

    public FechaValor(String fecha, int valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    public int getId_fechavalor() {
        return id_fechavalor;
    }

    public void setId_fechavalor(int id_fechavalor) {
        this.id_fechavalor = id_fechavalor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}