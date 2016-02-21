package com.example.admin.pmdm_practica5_jaime;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Admin on 21/02/2016.
 */
public class Vista extends View {

    private Context c;
    private Bitmap mapaDeBits;
    private Canvas lienzoFondo;
    private int ancho,alto;
    private float x0,y0,xi,yi;
    private Path rectaPoligonal = new Path();
    private int tamaño, rellenar;
    private SharedPreferences prefs;


    public Vista(Context contexto) {
        super(contexto);
        c=contexto;
        tamaño =1;
        rellenar=0;
    }

    /***********************************************************************/
    /***********************************************************************/
    /***********************************************************************/

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ancho = w;
        alto = h;
        mapaDeBits = Bitmap.createBitmap(w, h,
                Bitmap.Config.ARGB_8888);
        lienzoFondo = new Canvas(mapaDeBits);
    }

    @Override
    public void onDraw(Canvas lienzo) {
        lienzo.drawBitmap(mapaDeBits, 0, 0, null);

        Paint pincel = obtenerPincel();
        dibujar(lienzo, pincel);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0=x;
                y0=y;
                xi=x;
                yi=y;
                if(prefs.getInt("forma",-1) ==3)
                    rectaPoligonal.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                if(prefs.getInt("forma",-1) ==3)
                    rectaPoligonal.quadTo(xi, yi,(x + xi) / 2, (y + yi) / 2);
                xi=x;
                yi=y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                guardar();
                break;
        }
        return true;
    }

    /***********************************************************************/
    /***********************************************************************/
    /***********************************************************************/

    private void dibujar(Canvas lienzo, Paint pincel){
        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        switch (prefs.getInt("forma",-1)){
            case 0://rectangulo
                lienzo.drawRect(Math.min(x0, xi), Math.min(y0, yi), Math.max(x0, xi), Math.max(y0, yi), pincel);
                break;
            case 1://circulo
                lienzo.drawCircle(x0, y0, (float) Math.sqrt(Math.pow(x0 - xi, 2) + Math.pow(y0 - yi, 2)), pincel);
                break;
            case 2://recta
                lienzo.drawLine(x0, y0, xi, yi, pincel);
                break;
            case 3://linea
                lienzo.drawPath(rectaPoligonal, pincel);
                break;
            default://recta
                lienzo.drawLine(x0, y0, xi, yi, pincel);
        }
    }

    private void guardar(){
        Paint pincel=obtenerPincel();
        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        switch (prefs.getInt("forma",-1)){
            case 0://rectangulo
                lienzoFondo.drawRect(Math.min(x0, xi), Math.min(y0, yi), Math.max(x0, xi), Math.max(y0, yi), pincel);
                break;
            case 1://circulo
                lienzoFondo.drawCircle(x0, y0, (float) Math.sqrt(Math.pow(x0 - xi, 2) + Math.pow(y0 - yi, 2)), pincel);
                break;
            case 2://recta
                lienzoFondo.drawLine(x0, y0, xi, yi, pincel);
                break;
            case 3://linea
                lienzoFondo.drawPath(rectaPoligonal, pincel);
                rectaPoligonal.reset();
                break;
            default://recta
                lienzoFondo.drawLine(x0, y0, xi, yi, pincel);
        }
    }

    private Paint obtenerPincel(){
        Paint pincel=new Paint();
        pincel.setColor(obtenerColor());
        pincel.setStrokeWidth(obtenerTamaño());
        pincel.setAntiAlias(true);
        pincel.setStyle(rellenar());

        return pincel;
    }

    private float obtenerTamaño() {
        float size;
        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        switch (prefs.getInt("tamaño",-1)){
            case 0:
                size= (float) 1.5;
                break;
            case 1:
                size=(float) 2.5;
                break;
            case 2:
                size= (float) 4.5;
                break;
            case 3:
                size= (float) 6;
                break;
            default:
                size= (float) 1.5;
        }
        return size;
    }

    private int obtenerColor(){
        int aux;
        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        switch (prefs.getInt("color",-1)){
            case 0://rojo
                aux= Color.RED;
                break;
            case 1://azul
                aux=Color.BLUE;
                break;
            case 2://verde
                aux=Color.GREEN;
                break;
            case 3://blanco
                aux=Color.WHITE;
                break;
            default://negro
                aux=Color.BLACK;
        }
        return aux;
    }

    private Paint.Style rellenar(){
        Paint.Style i;
        prefs = c.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        switch (prefs.getInt("relleno",-1)){
            case 0://figura sin relleno, linea normal
                i=Paint.Style.STROKE;
                break;
            case 1://figura con relleno, linea con traza(sombra)
                i=Paint.Style.FILL;
                break;
            default://figura sin relleno, linea normal
                i=Paint.Style.STROKE;
        }
        return i;
    }
}