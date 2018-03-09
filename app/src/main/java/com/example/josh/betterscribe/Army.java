package com.example.josh.betterscribe;

/**
 * Created by Josh on 2018-03-09.
 */

public class Army {
    public String name;
    public int points;

    public Army() {
        super();
    }


    public Army(String n, int p){
        name = n;
        points = p;
    }

    @Override
    public String toString(){
        return this.name+' '+this.points;
    }
}
