package poorunistudents.betterscribe;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import poorunistudents.betterscribe.Army;

public class MainActivity extends AppCompatActivity {

    //Want to be able to access the extendible army Lists
    ListView ArmyLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArmyLists = (ListView) findViewById(R.id.ArmyLists);

        //need to populate the List
        Army[] Lists = {
                new Army(1,"Edge Lords", 1000),
                new Army(2, "Queen Cucks", 1500),
                new Army(3, "REEEE tards", 2000)
        };

        ArrayAdapter<Army> adapter = new ArrayAdapter<Army>(this, android.R.layout.simple_list_item_1,Lists);
        ArmyLists.setAdapter(adapter);

        //now we can set the onclick listener
        ArmyLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //What happens when we click on an item.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = ((TextView)view).getText().toString();
                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });

    }



}
