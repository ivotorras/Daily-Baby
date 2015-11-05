package com.dailybaby;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dataBase.BaseDeDatos;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class newBaby extends ActionBarActivity {

    private EditText peso, altura, sexo, nombre, dia;
    private Button btn;
    private DatePickerDialog datePickerDialog;
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(TimeZone.getDefault());
        setContentView(R.layout.activity_new_baby);
        btn = (Button)findViewById(R.id.btn);
        peso = (EditText)findViewById(R.id.txtpeso);
        altura = (EditText)findViewById(R.id.txtAltura);
        sexo = (EditText)findViewById(R.id.txtSexo);
        nombre = (EditText)findViewById(R.id.txtname);
        dia = (EditText)findViewById(R.id.txtDia);
        sexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());

                final CharSequence[] items = new CharSequence[2];

                items[0] = "Varon";
                items[1] = "Mujer";


                mBuilder.setTitle("Seleccionar Sexo")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sexo.setText(items[which].toString());
                                switch (which) {
                                    case 0:
                                        chCeleste();
                                        break;
                                    case 1:
                                        chRosa();
                                        break;
                                }

                            }
                        });

                mBuilder.show();
            }
        });


        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //dateCalendar.setTimeZone(TimeZone.getDefault());
                        String dateString = DateUtils.formatDateTime(view.getContext(), calendar.getTimeInMillis(), DateUtils.FORMAT_NUMERIC_DATE);
                        /*String ano0 = dateString.split("/")[2];
                        String dia0 = dateString.split("/")[1];
                        String mes0 = dateString.split("/")[0];
                        dia.setText(dia0+"/"+mes0+"/"+ano0);*/

                        //Reordeno la fecha a dd/mm/aa
                        String[] tmpArray = dateString.split("/");
                        System.out.println(Arrays.toString(tmpArray));
                        String ret = "";

                        String tmp = tmpArray[0];
                        tmpArray[0] = tmpArray[1];
                        tmpArray[1] = tmp;

                        for (int i = 0; i<tmpArray.length;i++){
                            ret+= tmpArray[i];
                            if (!(i==tmpArray.length-1)){
                                ret+="/";
                            }
                        }

                        dia.setText(ret);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre0, sexo0, fec0;
                int peso0, altura0;

                try {
                    nombre0 = nombre.getText().toString();
                    peso0 = Integer.parseInt(peso.getText().toString());
                    altura0 = Integer.parseInt(altura.getText().toString());
                    sexo0 = sexo.getText().toString();
                    fec0 = dia.getText().toString();

                    BaseDeDatos dab = new BaseDeDatos(view.getContext());
                    SQLiteDatabase db = dab.getWritableDatabase();
                    ContentValues rg = new ContentValues();
                    //rg.put("id" , 1);
                    rg.put("name", nombre0);
                    rg.put("sexo", sexo0);
                    rg.put("height", peso0);
                    rg.put("weight", altura0);
                    rg.put("date", fec0);
                    long i = db.insert("Bebes", null, rg);
                    db.close();


                    if (i > 0) {
                        Toast.makeText(view.getContext(), "Creado", Toast.LENGTH_SHORT).show();
                        Intent newAct= new Intent(newBaby.this, Principal.class );
                        startActivity(newAct);

                    }

                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "No Creado", Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_baby, menu);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void chCeleste(){
        Window w = getWindow();
        w.setStatusBarColor(this.getResources().getColor(R.color.stsCeleste));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.celeste)));
        //fab.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.stsCeleste)));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void chRosa(){
        Window w = getWindow();
        w.setStatusBarColor(this.getResources().getColor(R.color.stsRosa));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.rosa)));
        //fab.setBackgroundTintList(ColorStateList.valueOf(this.getResources().getColor(R.color.stsRosa)));
    }
}


