package com.example.josh.betterscribe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.josh.betterscribe.Army;

import java.util.ArrayList;
import java.util.List;


import static android.support.v7.appcompat.R.styleable.View;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    //ini
    LinearLayout armyListView;
    ArrayList<Army> armyList = new ArrayList<>();
    //need to populate list, god help my soul


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        armyListView  = (LinearLayout) findViewById(R.id.armies);

        Button addArmyButton = (Button) findViewById(R.id.add_army_button);
        addArmyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Create Army");
                builder.setMessage("Name your army and set your point limit.");
                //create an edit text for name
                final EditText armyName = new EditText(MainActivity.this);
                final EditText armyPoints = new EditText(MainActivity.this);
                final LinearLayout dialogView = new LinearLayout(MainActivity.this);
                dialogView.addView(armyName);
                dialogView.addView(armyPoints);
                dialogView.setVisibility(VISIBLE);
                builder.setView(dialogView);
                builder.setPositiveButton(R.string.done,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //need to get values from the view and create a new army and then start the new activity.
                                String userText = armyName.getText().toString();
                                String pointLimit = armyPoints.getText().toString();
                                int points = Integer.parseInt(pointLimit);
                                Army newArmy = new Army(userText,points);
                                //armyList.add(newArmy);
                                //finish();
                                Intent i = new Intent(MainActivity.this,ArmyView.class);
                                i.putExtra("Army",newArmy);
                                dialog.cancel();
                                startActivityForResult(i,123);
                            }
                        });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                //dialog.dismiss();
            }
        });

        //need to populate the linear layout with our armies.
        populateLayout();
        String armies = new String();
        for(Army a: armyList){
            armies += a.name;
        }

    }

    public void populateLayout(){
        for(final Army a: armyList){
            if(a.isDrawn) continue;
            LinearLayout armyLayout = new LinearLayout(this);
            armyLayout.setOrientation(LinearLayout.HORIZONTAL);
            //TO DO
            //This might cause problems because we might not want to wrap content.
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//unitlayoutparam
            LinearLayout.LayoutParams tvp = new LinearLayout.LayoutParams(775, ViewGroup.LayoutParams.WRAP_CONTENT);//textview param
            LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//button param
            armyLayout.setLayoutParams(p);

            final TextView tv = new TextView(MainActivity.this);
            final Button delButton = new Button(MainActivity.this);
            tv.setText("     "+a.name+"\n     "+a.points+"/"+a.maxPoints+" Points");
            if(a.points > a.maxPoints){
                tv.setBackgroundColor(Color.RED);
                tv.setTextColor(Color.WHITE);
            }
            tv.setLayoutParams(tvp);
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Intent intent = new Intent(MainActivity.this,ArmyView.class);
                    Army passedArmy = a;
                    armyList.remove(armyList.indexOf(a)); // dont want duplicates
                    intent.putExtra("Army",passedArmy);
                    delButton.setVisibility(android.view.View.GONE);
                    tv.setVisibility(android.view.View.GONE);
                    startActivityForResult(intent,123);
                }
            });
            delButton.setText("X");
            delButton.setBackgroundColor(Color.RED);
            delButton.setLayoutParams(bp);
            delButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirm Deletion");
                    builder.setMessage("Are you sure you want to delete this Army?");
                    builder.setPositiveButton(R.string.confirm,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    armyList.remove(armyList.indexOf(a));
                                    delButton.setVisibility(android.view.View.GONE);
                                    tv.setVisibility(android.view.View.GONE);
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

            armyLayout.addView(tv);
            armyLayout.addView(delButton);
            armyLayout.setVisibility(android.view.View.VISIBLE);
            armyListView.addView(armyLayout);
            a.isDrawn = true;
        }
    }

    //TO DO
    //Need to be able to get army being passed back from army view onBackpress
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){//in the life cycle this is where we want to load intents
        Log.d("Dev", "In on activity result...");
        super.onActivityResult(requestCode,resultCode,data);
        resultCode = RESULT_OK;
        //check if there is an intent for us, if so add the army into our armylist
        if(requestCode==123) {
            Log.d("Dev", "Request code 123...");
            if (resultCode == Activity.RESULT_OK) {
                Log.d("Dev", "Activity result Okay...");
                if (data != null) {
                    Log.d("Dev", "Intent was not null...");
                    Army tempArmy = data.getParcelableExtra("Army");
                    armyList.add(tempArmy);
                    Log.d("Dev", tempArmy.name);
                } else {
                    Log.d("Dev", "Intent was empty...");
                }

            } else {
                Log.d("Dev", "Activity result NOT Okay..."+resultCode);
            }
        }
        startActivity(getIntent());
    }

    @Override
    public void onResume(){
        super.onResume();
        populateLayout();

        String armies = new String();
        for(Army a: armyList){
            armies += a.name;
        }


    }

}
