package com.example.admin.pmdm_practica5_jaime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private View vista;
    private LinearLayout canvas;
    private int actual=0;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        vista=new Vista(this);

        canvas= (LinearLayout) findViewById(R.id.canvas);
        canvas.addView(vista, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


    }

    public void limpiar(View v){
        vista=new Vista(this);
        canvas.removeAllViews();
        canvas.addView(vista, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
    public void color(View v){
        Intent i=new Intent(this,Color.class);
        startActivity(i);
    }
    public void dibujo(View v){
        Intent i=new Intent(this,Forma.class);
        startActivity(i);
    }
    public void tamaño(View v){
        Intent i=new Intent(this,Tamanio.class);
        startActivity(i);
    }


}
