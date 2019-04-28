package com.example.android.crosshopper;

public class Vozidlo {
    private float x;
    private float y;
    private int rychlost;
    private int smer;

    public Vozidlo(int ix, int ismer, int irychlost) {
        x = ix;
        smer = ismer;
        rychlost = irychlost;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSmer() {
        return smer;
    }

    public void PohniDoprava() {
        x = x + rychlost;
    }

    public void PohniDolava() {
        x = x - rychlost;
    }
}
