package com.example.josh.betterscribe;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.example.josh.betterscribe.Unit;

/**
 * Created by Josh on 2018-03-09.
 */

//make this parcelable so that we can pass it between intents
public class Army implements Parcelable {
    public String name;
    public int maxPoints;
    public int points;
    public List<Unit> composition = new ArrayList<>();
    public boolean isDrawn = false;

    /////////////////////////////////////////////////////////////////////
    //needed if we want to use parcels
    @Override
    public int describeContents() {
        return 0;
    }
    //this allows us to make parcels out of this object
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(maxPoints);
        dest.writeInt(points);
        dest.writeTypedList(composition);
    }

    //retrieving data from the parcel
    private Army(Parcel in){
        this.name=in.readString();
        this.maxPoints=in.readInt();
        this.points=in.readInt();
        in.readTypedList(composition, Unit.CREATOR);
    }

    public static final Parcelable.Creator<Army> CREATOR = new Parcelable.Creator<Army>() {
        @Override
        public Army createFromParcel(Parcel source) {
            return new Army(source);
        }

        @Override
        public Army[] newArray(int size) {
            return new Army[size];
        }
    };

    /////////////////////////////////////////////////////////////////////

    public Army() {
        super();
    }


    public Army(String n, int p){
        name = n;
        maxPoints = p;
        composition = new ArrayList<>();
        points = 0;
    }

    @Override
    public String toString(){
        return this.name+"    "+this.points+'/'+this.maxPoints;
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
        if(u != null){
            this.composition.add(u);
        }
        else Log.d("Dev tag", "FUUUUUCK");
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
