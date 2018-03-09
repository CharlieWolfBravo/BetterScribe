package com.example.josh.betterscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class ArmyView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army_view);

        Intent intent = getIntent();
        String armyName = intent.getStringExtra("Army");

        Army currentArmy;
        for (Army a:armies) {
            if(a.name == armyName){
                currentArmy= a;
            }
        }
    }
}
