package com.dailybaby;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
>>>>>>> 50c0f11e66f6a1babc511179359cfe81a7cdbc68
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dataBase.BaseDeDatos;

<<<<<<< HEAD
public class MainActivity extends Activity {
=======

public class MainActivity extends Activity {

    private TextView txt;
>>>>>>> 50c0f11e66f6a1babc511179359cfe81a7cdbc68

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        nuevotrhead();
    }

    private void nuevotrhead(){
        Thread timer= new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e);
                }finally {
                    Intent foo= new Intent(MainActivity.this, Principal.class );
                    startActivity(foo);
                }
            }
        };
        timer.start();
=======
        txt = (TextView)findViewById(R.id.textView);
>>>>>>> 50c0f11e66f6a1babc511179359cfe81a7cdbc68
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ok(View v){
        BaseDeDatos dab = new BaseDeDatos(this);
        SQLiteDatabase db = dab.getWritableDatabase();
        ContentValues rg =new ContentValues();
        rg.put("id" , 1);
        rg.put("name", "Bringas");
        rg.put("birth", "2015-09-24 15:45:00" );
        rg.put("height", 3);
        rg.put("weight", 40);
        db.insert("Bebes", null, rg);
        Cursor fila = db.rawQuery("SELECT name, birth, height, weight from Bebes", null);

        txt.setText(fila.getString(0));

        db.close();

    }
}
