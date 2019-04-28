package com.example.android.crosshopper;

import java.util.ArrayList;

public class Zviera {
    private float x, y;

    public Zviera() {
        Startuj();
    }

    public void Hore() {
        if (y > 0) y = y - 54;
    }

    public void Doprava() {
        if (x < 1026) x = x + 54;
    }

    public void Dolava() {
        if (x > 0) x = x - 54;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private boolean jeBlizko(float ax, float ay, float bx, float by) {
        double d = Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
        return d < 54;
    }


    public boolean Naraz(ArrayList<Vozidlo> zoznam) {
        for (int i = 0; i < zoznam.size(); i++) {
            Vozidlo ms = (Vozidlo) (zoznam.get(i));
            if (jeBlizko(ms.getX() * 54, ms.getY(), getX(), getY())) return true;
        }
        return false;
    }

    public void Startuj() {
        x = 10 * 54;
        y = 33 * 54;
    }

    public boolean vCieli() {
        if (y <= 0) return true;
        else return false;
    }

}
