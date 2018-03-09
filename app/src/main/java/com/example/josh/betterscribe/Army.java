package com.example.josh.betterscribe;

import java.util.List;
import java.util.Vector;

/**
 * Created by Josh on 2018-03-09.
 */

public class Army {
    public String name;
    private int maxPoints;
    private int points;
    public List<Unit> composition;

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
    };

    public String getArmyname() { return this.name;};

    //returns the maximum pooints that the army can consist of.
    public int getArmyMaxPoints() { return this.maxPoints;};

    //returns the current points in the army
    public int getArmyPoints() {
        int sum = 0;
        for (Unit unit:composition) {
            sum += unit.points;
        }
        return sum;
    }

    public void addUnit(Unit u){
        composition.add(u);
    }

    //returns a list of units that are only HQs in the composition
    public List<Unit> getHQs(){
        List<Unit> HQS = new Vector<Unit>();
        for (Unit u:composition) {
            if(u.type == "HQ"){
                HQS.add(u);
            }
        }
        return HQS;
    }
}
