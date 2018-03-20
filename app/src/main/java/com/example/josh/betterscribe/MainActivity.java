package com.example.josh.betterscribe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josh.betterscribe.Army;

import java.util.ArrayList;
import java.util.List;


import static android.support.v7.appcompat.R.styleable.View;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    //ini
    ListView armyListView;
    ArrayList<Army> armyList = new ArrayList<>();
    //need to populate list, god help my soul


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        armyListView  = (ListView) findViewById(R.id.armies);

        Button addArmyButton = (Button) findViewById(R.id.addArmyButton);
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
                                startActivityForResult(i,123);
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


        ArrayAdapter<Army> adapter = new ArrayAdapter<Army>(this,android.R.layout.simple_list_item_1, armyList);

        armyListView.setAdapter(adapter);

        armyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //what happens when we click an item
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id){
                Intent intent = new Intent(getBaseContext(),ArmyView.class);
                Army passedArmy = armyList.get(position);
                armyList.remove(position);
                intent.putExtra("Army",passedArmy);
                startActivityForResult(intent,123);
            }

        });

    }

    //TO DO
    //Need to be able to get army being passed back from army view onBackpress
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){//in the life cycle this is where we want to load intents
        Log.d("Dev", "In on activity result...");
        //check if there is an intent for us, if so add the army into our armylist
        if(requestCode==123) {
            Log.d("Dev", "Request code 123...");
            if (resultCode == Activity.RESULT_OK) {
                Log.d("Dev", "Activity result Okay...");
                if (data != null) {
                    Log.d("Dev", "Intent was not null...");
                    Army tempArmy = data.getParcelableExtra("Army");
                    armyList.add(tempArmy);
                } else {
                    Log.d("Dev", "Intent was empty...");
                }

            } else {
                Log.d("Dev", "Activity result NOT Okay..."+resultCode);
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}
