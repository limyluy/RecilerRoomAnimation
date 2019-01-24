package com.example.inteli5.recilerroomanimation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FechaValorViewModel extends AndroidViewModel {
    private FechaValorRepository fechaValorReposotory;
    private LiveData<List<FechaValor>> fecLiveData;
    private LiveData<List<FechaValor>> getFecLiveDataHoy;



    public FechaValorViewModel(@NonNull Application application){
        super(application);

        fechaValorReposotory = new FechaValorRepository(application);
        fecLiveData = fechaValorReposotory.getAllFechaVAlor();
        getFecLiveDataHoy =  fechaValorReposotory.getAllFechaValorHoy();
    }

    public void  insertarFechaValor(FechaValor fechaValor){
        fechaValorReposotory.insertarFechaValor(fechaValor);
    }

    public LiveData<List<FechaValor>> getFechaVAlor(){
        return fecLiveData;
    }
    public  LiveData<List<FechaValor>> getFechavalorHoy(){
        return getFecLiveDataHoy;
    }

}
