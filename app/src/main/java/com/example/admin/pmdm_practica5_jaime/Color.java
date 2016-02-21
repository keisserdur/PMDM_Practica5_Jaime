package com.example.admin.pmdm_practica5_jaime;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class Color extends AppCompatActivity {

    private RadioGroup rg;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();

        color=prefs.getInt("color", -1);

        rg= (RadioGroup) findViewById(R.id.radioGroup);
        switch (prefs.getInt("color", -1)){
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
                        color=0;//color rojo
                        break;
                    case R.id.radioButton2:
                        color=1;//color azul
                        break;
                    case R.id.radioButton3:
                        color=2;//color verde
                        break;
                    case R.id.radioButton4:
                        color=3;//color blanco
                        break;
                    case R.id.radioButton5:
                        color=-1;//color negro
                        break;
                }
            }
        });
    }
    public void cancelar(View v){
        finish();
    }
    public void aceptar(View v){
        editor.putInt("color",color);
        editor.commit();
        finish();
    }
}
