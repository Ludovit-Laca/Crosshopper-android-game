package com.example.android.crosshopper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends View {
    Paint paint;
    private int body = 0;
    private int mapa = 1;
    private int zivoty = 3;

    ArrayList<Vozidlo> zoznam;
    Zviera ja;
    String pole[];

    public Game(Context context) {
        super(context);
        paint = new Paint();
        zoznam = new ArrayList<Vozidlo>();
        pole = new String[34];
        ja = new Zviera();
        vygenerujMapu();
    }

    public void vygenerujMapu() {
        for (int i = 0; i < 34; i++) {
            if (i == 32 || i == 33) {
                pole[i] = "t";
            } else if (Math.random() > 0.4) {
                pole[i] = "a";
                int cislo = (int) (Math.random() * 21);
                int smer = (int) (Math.random() * 2);
                zoznam.add(new Vozidlo(cislo, smer, 1));
            } else pole[i] = "t";
        }
    }

    protected void onDraw(Canvas canvas) {
        Vozidlo vz;
        for (int i = 0; i < 34; i++) {
            if (pole[i] == "a") {
                paint.setColor(Color.BLACK);
            } else {
                paint.setColor(Color.rgb(60, 232, 114));
            }
            for (int j = 0; j < 21; j++) {
                canvas.drawRect(j * 54, i * 54, (j + 1) * 54, (i + 1) * 54, paint);
            }
        }

        paint.setColor(Color.WHITE);
        int j = 0;
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == "a") {
                vz = (Vozidlo) (zoznam.get(j));
                vz.setY(i * 54);
                canvas.drawRect(vz.getX() * 54, i * 54, (vz.getX() + 1) * 54, (i + 1) * 54, paint);
                j++;
            }
        }

        paint.setColor(Color.BLUE);
        canvas.drawRect(ja.getX(), ja.getY(), ja.getX() + 54, ja.getY() + 54, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(35);
        canvas.drawText("Skore: " + body, 0, 1865, paint);
        canvas.drawText("Životy: " + zivoty, 490, 1865, paint);
        canvas.drawText("Svet: " + mapa + ".", 950, 1865, paint);
    }

    public void update() {
        Vozidlo vz;
        if (zoznam.size() > 0) {
            for (int i = zoznam.size() - 1; i >= 0; i--) {
                vz = (Vozidlo) (zoznam.get(i));
                if (vz.getSmer() == 0) {
                    vz.PohniDoprava();
                } else {
                    vz.PohniDolava();
                }
                if (vz.getX() < 0) {
                    vz.setX(21);
                } else if (vz.getX() > 21) {
                    vz.setX(0);
                }
            }
        }

        if (ja.Naraz(zoznam)) {
            Context mContext = this.getContext();
            if (zivoty == 1) {
                Toast.makeText(mContext, "Game Over!", Toast.LENGTH_SHORT).show();
                body = 0;
                mapa = 1;
                zivoty = 3;
            } else {
                Toast.makeText(mContext, "CRASH!!!", Toast.LENGTH_SHORT).show();
                zivoty--;
            }
            ja.Startuj();
        }

        if (ja.vCieli()) {
            body = body + 100;
            mapa++;
            Context mContext = this.getContext();
            Toast.makeText(mContext, "YEAH!!", Toast.LENGTH_SHORT).show();
            zoznam.clear();
            vygenerujMapu();
            ja.Startuj();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int e = event.getAction();
        if (e == MotionEvent.ACTION_DOWN) {
            float iy = event.getY();
            float ix = event.getX();

            if (ix > 860) ja.Doprava();
            else if (ix < 220) ja.Dolava();
            else {
                ja.Hore();
                body++;
            }
        }
        invalidate();
        return true;
    }
}
