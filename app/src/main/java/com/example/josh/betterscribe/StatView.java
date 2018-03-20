package com.example.josh.betterscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StatView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_view);

        Intent intent = getIntent();
        Unit tempU = (Unit) intent.getParcelableExtra("Unit");
        Log.d("dev", tempU.m + " " + tempU.ws);
        if(tempU != null){
            TextView m = (TextView) findViewById(R.id.M_value_view);
            m.setText(Integer.toString(tempU.m)+"\"");
            TextView ws = (TextView) findViewById(R.id.WS_value_view);
            ws.setText(Integer.toString(tempU.ws)+"+");
            TextView bs= (TextView) findViewById(R.id.BS_value_view);
            bs.setText(Integer.toString(tempU.bs)+"+");
            TextView s = (TextView) findViewById(R.id.S_value_view);
            s.setText(Integer.toString(tempU.s));
            TextView t = (TextView) findViewById(R.id.T_value_view);
            t.setText(Integer.toString(tempU.t));
            TextView w = (TextView) findViewById(R.id.W_value_view);
            w.setText(Integer.toString(tempU.w));
            TextView a = (TextView) findViewById(R.id.A_value_view);
            a.setText(Integer.toString(tempU.a));
            TextView ld = (TextView) findViewById(R.id.Ld_value_view);
            ld.setText(Integer.toString(tempU.ld));
            TextView sv = (TextView) findViewById(R.id.Sv_value_view);
            sv.setText(Integer.toString(tempU.sv)+"+");
            TextView name = (TextView) findViewById(R.id.unit_name_view);
            name.setText(tempU.name);
        }
    }
}
