package com.example.admin.pmdm_practica5_jaime;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Forma extends AppCompatActivity {

    private RadioGroup rg,rg2;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int forma,relleno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();

        forma=prefs.getInt("forma", -1);
        relleno=prefs.getInt("relleno", -1);


        rg = (RadioGroup) findViewById(R.id.radioGroup);
        switch (prefs.getInt("forma", -1)){
            case 0:
                rg.check(R.id.radioButton);
                break;
            case 1:
                rg.check(R.id.radioButton2);
                break;
            case 2:
                rg.check(R.id.radioButton3);
                break;
            case 3:
                rg.check(R.id.radioButton4);
                break;
            default:
                rg.check(R.id.radioButton3);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton:
                        forma=0;//forma cuadrada
                        break;
                    case R.id.radioButton2:
                        forma=1;//forma circular
                        break;
                    case R.id.radioButton3:
                        forma=2;//forma recta
                        break;
                    case R.id.radioButton4:
                        forma=3;//forma linea
                        break;
                }
            }
        });

        /*********************************************************************/

        rg2= (RadioGroup) findViewById(R.id.radioGroup2);
        switch (prefs.getInt("relleno", -1)){
            case 0:
                rg2.check(R.id.radioButton7);
                break;
            case 1:
                rg2.check(R.id.radioButton6);
                break;
            default:
                rg2.check(R.id.radioButton6);
        }
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton7:
                        relleno=0;//con relleno
                        break;
                    case R.id.radioButton6:
                        relleno=1;//sin relleno
                        break;
                }
            }
        });

    }
    public void cancelar(View v){
        finish();
    }
    public void aceptar(View v){
        editor.putInt("forma",forma);
        editor.putInt("relleno",relleno);
        editor.commit();
        finish();
    }
}
