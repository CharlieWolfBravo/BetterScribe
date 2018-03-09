package com.example.josh.betterscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josh.betterscribe.Army;

import java.util.ArrayList;
import java.util.List;


import static android.support.v7.appcompat.R.styleable.View;

public class MainActivity extends AppCompatActivity {

    //ini
    ListView armyList;
    ArrayList<Army> armies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        armyList  = (ListView) findViewById(R.id.armies);
        final Army cucks = new Army("Cucks", 1000);
        cucks.addUnit(new Unit("Custodian Wardens", 30, "Elite", 6,2,2,5,5,3,4,9,2));
        Army[] array = { cucks };

        //function to get the current armies from a saved file
    /*
        //get the file from memory
        File file = new File(context.getFilesDir(), filename);
        //Read text from file
        StringBuilder text = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        //

    while ((line = br.readLine()) != null) {
        text.append(line);
        text.append('\n');
    }
    br.close();

    */

        ArrayAdapter<Army> adapter = new ArrayAdapter<Army>(this,android.R.layout.simple_list_item_1, array);

        armyList.setAdapter(adapter);

        armyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //what happens when we click an item
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id){
                Intent intent = new Intent(getBaseContext(),ArmyView.class);
                intent.putParcelableArrayListExtra("Army",cucks);
                startActivity(intent);
            }

        });

    }

    //TO DO:
    //Need to make army parcelable in order to pass the custom object between intents
    //from main_activity to army_view




}
