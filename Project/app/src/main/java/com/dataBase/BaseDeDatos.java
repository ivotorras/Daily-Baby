package com.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by agus on 24/09/15.
 */
public class BaseDeDatos extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dailyBaby.db";

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXIST Bebes(id INT primary key  NOT NULL, name TEXT, birth DATETIME, height INT, weight INT)");
        db.execSQL("CREATE TABLE IF NOT EXIST Actividades(id INT primary key NOT NULL, nameAct TEXT, date DATETIME, idBebe INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVesion) {
        //TODO Para actualizar Db
        //
    }


}
