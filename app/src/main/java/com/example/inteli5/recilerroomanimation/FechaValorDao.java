package com.example.inteli5.recilerroomanimation;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.util.Log;

import java.util.Date;
import java.util.List;
@Dao
public interface FechaValorDao {




    @Insert
    void insertarFechaValor(FechaValor fechaValor);

    @Query("SELECT * FROM FechaValor")
    LiveData<List<FechaValor>> getFechavalor();

    @Query("SELECT * FROM fechavalor WHERE fecha = :fecha")
    LiveData<List<FechaValor>> getFechaValorHoy(String fecha);

    @Query("DELETE  FROM fechavalor WHERE id_fechavalor = :id")
    void deleteFechaValor(int id);

}
