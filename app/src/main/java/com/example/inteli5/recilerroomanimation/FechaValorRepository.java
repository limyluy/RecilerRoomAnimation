package com.example.inteli5.recilerroomanimation;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class FechaValorRepository {
    private FechaValorDao fechaValorDao;
    private LiveData<List<FechaValor>> allFechaVAlor;
    private LiveData<List<FechaValor>> allFechaValorHoy;



    public FechaValorRepository(Application application){
        AllData  allData = AllData.getInstance(application);
        fechaValorDao = allData.fechaValorDao();
        allFechaVAlor = fechaValorDao.getFechavalor();
        allFechaValorHoy = fechaValorDao.getFechaValorHoy(MainActivity.fecha);
    }

    public void insertarFechaValor(FechaValor fechaValor){
        new InsertarFechaValorAsynTask(fechaValorDao).execute(fechaValor);
    }

    public LiveData<List<FechaValor>> getAllFechaVAlor(){
        return allFechaVAlor;
    }

    public LiveData<List<FechaValor>> getAllFechaValorHoy(){
        return allFechaValorHoy;
    }

    public void deleteFechaValor(){
        return;
    }


    private static class InsertarFechaValorAsynTask extends AsyncTask<FechaValor,Void,Void>{
        private FechaValorDao fechaValorDao;

        public InsertarFechaValorAsynTask(FechaValorDao fechaValorDao) {
            this.fechaValorDao = fechaValorDao;
        }

        @Override
        protected Void doInBackground(FechaValor... fechaValors) {
            fechaValorDao.insertarFechaValor(fechaValors[0]);
            return null;
        }
    }
}