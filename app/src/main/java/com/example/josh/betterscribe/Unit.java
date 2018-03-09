package com.example.josh.betterscribe;

/**
 * Created by Lance on 2018-03-09.
 */

public class Unit {
    public String name;
    public int points;
    public String type;
    public int m, ws, bs, s, t, w, a, ld, sv;

    public Unit(String N, int p, String type, int m, int ws, int bs, int s, int t, int w, int a, int ld, int sv){
        name = N;
        points = p;
        this.type = type;
        this.m = m;
        this.ws = ws;
        this.bs = bs;
        this.s = s;
        this.t = t;
        this.w = w;
        this.a = a;
        this.ld = ld;
        this.sv = sv;
    }
}
