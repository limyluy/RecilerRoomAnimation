package com.example.inteli5.recilerroomanimation;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Database(entities = {FechaValor.class},version = 1)
public abstract class AllData extends RoomDatabase {

    private static final String DB_NAME = "database.db";
    private static volatile AllData instance;

    public abstract FechaValorDao fechaValorDao();

    public static synchronized AllData getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AllData.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabase)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabase = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PupolateFechaValorAsyntask(instance).execute();
        }
    };

    private static class PupolateFechaValorAsyntask extends AsyncTask<Void,Void,Void>{
        FechaValorDao fechaValorDao;

        public PupolateFechaValorAsyntask(AllData allData) {
            this.fechaValorDao = allData.fechaValorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Calendar c = Calendar.getInstance();
            c.setFirstDayOfWeek(Calendar.MONDAY);
            Date dat = c.getTime();
            SimpleDateFormat ftt = new SimpleDateFormat("EE dd MMM yyyy");

            String date = String.valueOf(ftt.format(dat));
            Date da2;
            da2 = sumarDiasAFecha(dat,1);
            String date1 = ftt.format(da2);
            fechaValorDao.insertarFechaValor(new FechaValor(date,1000));
            fechaValorDao.insertarFechaValor(new FechaValor(date,2000));
            fechaValorDao.insertarFechaValor(new FechaValor(date,3000));
            fechaValorDao.insertarFechaValor(new FechaValor(date,4000));
            fechaValorDao.insertarFechaValor(new FechaValor(date1,5000));
            fechaValorDao.insertarFechaValor(new FechaValor(date1,6000));

            return null;
        }

        public Date sumarDiasAFecha(Date fecha, int dias) {
            if (dias == 0) return fecha;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DAY_OF_YEAR, dias);
            return calendar.getTime();
        }

    }





}
