package com.dailybaby;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Principal extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private ArrayList<Item_objct> BarraItms;
    private TypedArray NavIcons;
    private ListView NavList;
    NavigationAdapter Adaptador;
    private String[] titulos;
    private ListView ListDef;
    private android.support.design.widget.FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        chCeleste();
        fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ListDef = (ListView) findViewById(R.id.navigation_drawer);
        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header

        ListDef.addHeaderView(header);
        //Tomamos listado  de imgs desde drawable

        NavIcons = getResources().obtainTypedArray(R.array.iconos);

        titulos = getResources().getStringArray(R.array.estrings);

        BarraItms = new ArrayList<Item_objct>();

        BarraItms.add(new Item_objct(titulos[0], NavIcons.getResourceId(0, -1)));

        BarraItms.add(new Item_objct(titulos[1], NavIcons.getResourceId(1, -1)));

        BarraItms.add(new Item_objct(titulos[2], NavIcons.getResourceId(2, -1)));

        BarraItms.add(new Item_objct(titulos[3], NavIcons.getResourceId(3, -1)));

        Adaptador= new NavigationAdapter(this,BarraItms);
        ListDef.setAdapter(Adaptador);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
            return rootView;
        }

       /* @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Principal) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }*/
    }

    public AlertDialog ok(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final CharSequence[] items = new CharSequence[3];

        items[0] = "Rosa";
        items[1] = "Celeste";
        items[2] = "Nuevo Bebe";


        builder.setTitle("Diálogo De Lista")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chRosa();
                                break;
                            case 1:
                                chCeleste();
                                break;
                            case 2:
                                dialog.dismiss();
                                createLoginDialogo();
                                break;
                        }
                    }
                });

        return builder.show();
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


    public void createLoginDialogo() {

        final EditText peso, altura, sexo, nombre, dia;
        final View v;
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        v = inflater.inflate(R.layout.newbaby, null);
        peso = (EditText)findViewById(R.id.txtpeso);
        altura = (EditText)findViewById(R.id.txtAltura);
        sexo = (EditText)findViewById(R.id.txtSexo);
        nombre = (EditText)findViewById(R.id.txtname);
        dia = (EditText)findViewById(R.id.txtDia);

        sexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        builder.setView(v);


        builder.show();

    }


    public AlertDialog setSexo(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getApplicationContext());

        final CharSequence[] items = new CharSequence[2];

        items[0] = "Macho";
        items[1] = "Hembra";


        mBuilder.setTitle("Seleccionar Sexo")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //sexo.setText(items[which].toString());
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),items[which].toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        return mBuilder.show();
    }

}
