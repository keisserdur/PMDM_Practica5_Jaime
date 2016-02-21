package com.example.admin.pmdm_practica5_jaime;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Tamanio extends AppCompatActivity {

    private RadioGroup rg;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int tamaño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamanio);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();

        tamaño=prefs.getInt("tamaño", -1);

        rg= (RadioGroup) findViewById(R.id.radioGroup);
        switch (prefs.getInt("tamaño", -1)){
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
                rg.check(R.id.radioButton);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton:
                        tamaño=0;//superfino
                        break;
                    case R.id.radioButton2:
                        tamaño=1;//fino
                        break;
                    case R.id.radioButton3:
                        tamaño=2;//medio
                        break;
                    case R.id.radioButton4:
                        tamaño=3;//grueso
                        break;
                }
            }
        });
    }
    public void cancelar(View v){
        finish();
    }
    public void aceptar(View v){
        editor.putInt("tamaño",tamaño);
        editor.commit();
        finish();
    }
}
