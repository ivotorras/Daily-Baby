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
        db.execSQL("CREATE TABLE IF NOT EXISTS Bebes(id INTEGER PRIMARY KEY AUTOINCREMENT, name Text,date Text, height INTEGER, weight INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Actividades(id INTEGER PRIMARY KEY AUTOINCREMENT, nameAct Text, date Text, idBebe INTEGER)");

    }
    /* public void create(View view){
    *  try{
            BaseDeDatos dab = new BaseDeDatos(this);
            SQLiteDatabase db = dab.getWritableDatabase();
            ContentValues rg =new ContentValues();
            //rg.put("id" , 1);
            rg.put("name", "Bringas");
            rg.put("height", 3);
            rg.put("weight", 40);
            rg.put("date", "8:09 AM");
            long i = db.insert("Bebes", null, rg);
            db.close();


            if (i>0){
                    Toast.makeText(this,"Creado", LENGTH_SHORT).show();
                }

        }
        catch(Exception e){
            System.out.println(e);
            Toast.makeText(this,"No Creado", LENGTH_SHORT).show();
        }


     public void delete(int id) {
        BaseDeDatos admin = new BaseDeDatos(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.delete("Bebes", "id=" + id, null);
        bd.close();
    }


        public void listar(){
        try{BaseDeDatos dab = new BaseDeDatos(this);
            SQLiteDatabase db = dab.getReadableDatabase();
            Cursor fila = db.rawQuery("SELECT * from Bebes", null);
            int cant = fila.getCount();
            int i = 0;
            String[] arr = new String[cant];
            if (fila.moveToFirst()){
                do {

                    String linea = fila.getInt(0)+" "+fila.getString(1);
                    arr [i] = linea;
                    i++;
                }while(fila.moveToNext());{

                }
                ArrayAdapter<String > adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arr);
                list.setAdapter(adapter);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVesion) {
        //TODO Para actualizar Db
        //
    }



}
