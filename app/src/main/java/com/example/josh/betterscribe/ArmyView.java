package com.example.josh.betterscribe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.sql.Types.NULL;


public class ArmyView extends AppCompatActivity {
    Army currentArmy = new Army();
    int currentID =  0;
    ArrayList<Unit> masterList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army_view);
        //create master list
        createMasterList();
        Intent intent = getIntent();
        Army tempArmy = (Army) intent.getParcelableExtra("Army");
        if(tempArmy != null){
            currentArmy.name = tempArmy.name;
            currentArmy.maxPoints = tempArmy.maxPoints;
            currentArmy.points = tempArmy.points;
            currentArmy.composition = tempArmy.composition;
        }
        populateList();
    }
    public void populateList(){
        //hq
        for(Unit u:currentArmy.composition){
            //Log.d("dev", "Adding HQs to "+currentArmy.name);
            //Log.d("dev", u.name + " " + u.type);
            addUnitLine(u);
        }
        //need to look at HQs add button
        unitChoiceAlert("HQ");
        unitChoiceAlert("TROOP");
        unitChoiceAlert("ELITE");
        unitChoiceAlert("FAST");
        unitChoiceAlert("HEAVY");
        unitChoiceAlert("FLYER");
        unitChoiceAlert("TRANSPORT");
    }
    @Override
    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Army",currentArmy);
        returnIntent.putExtra("HAHA","FUCK YOU");
        setResult(RESULT_OK, returnIntent);
        Log.d("Dev", "Result being sent back: "+RESULT_OK);
        String list = new String();
        for(Unit u: currentArmy.composition){
            list += u.name;
        }
        if(returnIntent.getParcelableExtra("Army") == null){
            Log.d("Dev","FUCK");
        }else{
            Army fag = returnIntent.getParcelableExtra("Army");

            Log.d("Dev",fag.name+" "+fag.points);
        }

        Toast t = Toast.makeText(ArmyView.this,list,Toast.LENGTH_LONG);
        //t.show();
        //super.onBackPressed();
        finish();
    }
    public void addUnitLine(Unit U){
        final Unit unitCopy = U;

        LinearLayout Layout = new LinearLayout(ArmyView.this);
        switch (U.type){
            case "HQ":
                 Layout = (LinearLayout) findViewById(R.id.hq_vert_layout);
                break;
            case "ELITE":
                Layout = (LinearLayout) findViewById(R.id.elite_vert_layout);
                break;
            case "TROOP":
                Layout = (LinearLayout) findViewById(R.id.troop_vert_layout);
                break;
            case "FAST":
                Layout = (LinearLayout) findViewById(R.id.fast_attack_vert_layout);
                break;
            case "HEAVY":
                Layout = (LinearLayout) findViewById(R.id.heavy_support_vert_layout);
                break;
        }


        //need to add a linear layout
        LinearLayout unitLayout = new LinearLayout(this);
        unitLayout.setOrientation(LinearLayout.HORIZONTAL);
        //TO DO
        //This might cause problems because we might not want to wrap content.
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//unitlayoutparam
        LinearLayout.LayoutParams tvp = new LinearLayout.LayoutParams(775, ViewGroup.LayoutParams.WRAP_CONTENT);//textview param
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//button param
        unitLayout.setLayoutParams(p);
        //Now we can add and populate the layout

        //Textview
        final TextView tv = new TextView(this);
        tv.setText(U.name+"\n"+U.points+" Points");
        tv.setLayoutParams(tvp);
        tv.setId(currentID);
        currentID++;
        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //start new intent and pass the unit in the intent
                Intent intent = new Intent(ArmyView.this,StatView.class);
                intent.putExtra("Unit",unitCopy);
                startActivity(intent);
            }
        });

        //Delete Button
        final Button delButton = new Button(this);
        delButton.setText("X");
        delButton.setBackgroundColor(Color.RED);
        delButton.setLayoutParams(bp);
        delButton.setId(currentID);
        currentID++;
        delButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ArmyView.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Are you sure you want to delete this unit?");
                builder.setPositiveButton(R.string.confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //need to remove the unit from the composition list and refresh the page
                                currentArmy.points -= unitCopy.points;
                                currentArmy.composition.remove(currentArmy.composition.indexOf(unitCopy));
                                //need to remove from the view
                                delButton.setVisibility(View.GONE);
                                tv.setVisibility(View.GONE);

                            }
                        });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        unitLayout.addView(tv);
        unitLayout.addView(delButton);
        unitLayout.setId(currentID);
        currentID++;

        unitLayout.setVisibility(View.VISIBLE);
        Layout.addView(unitLayout);
    }

    public void createMasterList(){
        //Need to populate the master list.
        //HQs
        masterList.add(new Unit("Captain-General Trajann Valoris", 250, "HQ", 6,2,2,5,5,7,5,10,2));
        masterList.add(new Unit("Sheild Captain in Allarus Terminator Armour", 142, "HQ" , 6,2,2,5,5,7,5,9,2));
        masterList.add(new Unit("Sheild-Captain", 122, "HQ", 6,2,2,5,5,6,5,9,2));
        masterList.add(new Unit("Sheild-Captain on Dawneagle Jetbike", 160, "HQ", 14,2,2,5,6,7,5,9,2));
        //troops
        masterList.add(new Unit("Custodian Guard Squad", 156, "TROOP", 6,2,2,5,5,3,3,8,2));
        //Elites
        masterList.add(new Unit("Allarus Custodians", 252, "ELITE", 6,2,2,5,5,4,4,9,2));
        masterList.add(new Unit("Custodian Wardens", 201, "ELITE", 6,2,2,5,5,3,4,9,2));
        masterList.add(new Unit("Venerable Contemptor Dreadnought", 199, "ELITE", 9,2,2,7,7,10,4,8,3));
        masterList.add(new Unit("Vexillus Praetor", 112, "ELITE", 6,2,2,5,5,5,4,9,2));
        masterList.add(new Unit("Vexillus Praetor in Allarus Terminator Armour", 120, "ELITE", 6,2,2,5,5,6,4,9,2));
        //Fast
        masterList.add(new Unit("Vertus Praetors", 270, "FAST", 14,2,2,5,6,4,4,9,2));
        //Heavy
        masterList.add(new Unit("Venerable Land Raider", 400, "HEAVY", 10,6,2,8,8,16,6,9,2));
    }

    public void unitChoiceAlert(final  String type){
        Button add = new Button(this);
        switch(type) {
            case "HQ":
                add = (Button) findViewById(R.id.hq_add_button);
                break;
            case "ELITE":
                add = (Button) findViewById(R.id.elite_add_button);
                break;
            case "TROOP":
                add = (Button) findViewById(R.id.troop_add_button);
                break;
            case "FAST":
                add = (Button) findViewById(R.id.fast_attack_add_button);
                break;
            case "HEAVY":
                add = (Button) findViewById(R.id.heavy_support_add_button);
                break;
            case "FLYER":
                add = (Button) findViewById(R.id.flyer_add_button);
                break;
            case "TRANSPORT":
                add = (Button) findViewById(R.id.transport_add_button);
                break;
        }

        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){

                final ArrayList<String> selection = new ArrayList<>();
                //need to build a aert dialog thingy what ya call it
                final AlertDialog.Builder adb = new AlertDialog.Builder(ArmyView.this);
                for(Unit u:masterList){
                    if(u.type == type){
                        selection.add(u.name+"\n"+u.points);
                    }
                }
                Log.d("dev", "Selection size "+selection.size());
                final CharSequence items[] = selection.toArray(new CharSequence[selection.size()]);
                adb.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    //dumbest function you've ever seen
                    @Override
                    public void onClick(DialogInterface d, int n) {
                        int selection = 0;
                        switch(type) {
                            case "HQ":
                                switch (n) {
                                    case 0:
                                        selection  = 0;
                                        break;
                                    case 1:
                                        selection = 1;
                                        break;
                                    case 2:
                                        selection = 2;
                                        break;
                                    case 3:
                                        selection = 3;
                                        break;
                                }
                                break;
                            case "ELITE":
                                switch (n) {
                                    case 0:
                                        selection = 5;
                                        break;
                                    case 1:
                                        selection = 6;
                                        break;
                                    case 2:
                                        selection = 7;
                                        break;
                                    case 3:
                                        selection = 8;
                                        break;
                                    case 4:
                                        selection = 9;
                                        break;
                                }
                                break;
                            case "TROOP":
                                selection = 4;
                                break;
                            case "FAST":
                                selection = 10;
                                break;
                            case "HEAVY":
                                selection = 11;
                                break;
                        }
                        //add selection and do things.
                        currentArmy.composition.add(masterList.get(selection));
                        addUnitLine(masterList.get(selection));
                        currentArmy.points += masterList.get(selection).points;
                        //we want to dismiss and then resart activity
                        d.dismiss();
                    }

                });
                adb.setNegativeButton(R.string.done, null);
                adb.setTitle("Which one?");
                AlertDialog dialog = adb.create();
                dialog.show();
            }
        });
    }
}
