package com.example.josh.betterscribe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lance on 2018-03-09.
 */

public class Unit implements Parcelable {
    public String name;
    public int points;
    public String type;
    public int m, ws, bs, s, t, w, a, ld, sv;

    //Parcel Stuff//////////////////////////////////////////////////////////////////////////////////
    @Override
    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeInt(points);
        dest.writeString(type);
        dest.writeInt(m);
        dest.writeInt(ws);
        dest.writeInt(bs);
        dest.writeInt(s);
        dest.writeInt(t);
        dest.writeInt(w);
        dest.writeInt(a);
        dest.writeInt(ld);
        dest.writeInt(sv);

    }
    private Unit(Parcel in){
        this.name = in.readString();
        this.points = in.readInt();
        this.type = in.readString();
        this.m = in.readInt();
        this.ws= in.readInt();
        this.bs=in.readInt();
        this.s=in.readInt();
        this.t=in.readInt();
        this.w=in.readInt();
        this.a=in.readInt();
        this.ld=in.readInt();
        this.sv=in.readInt();
    }

    public static final Parcelable.Creator<Unit> CREATOR = new Parcelable.Creator<Unit>() {
        @Override
        public Unit createFromParcel(Parcel source) {
            return new Unit(source);
        }

        @Override
        public Unit[] newArray(int size) {
            return new Unit[size];
        }
    };
    ////////////////////////////////////////////////////////////////////////////////////////////////

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

    public Unit() { super(); }
}
