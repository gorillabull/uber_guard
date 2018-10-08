package com.android.admin.uber_guard;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.net.HttpURLConnection;

public class Act2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2);


        ///create the data for the listview and the listview itself
        //------------------------------------------------
        List<String> datum = new ArrayList<>();
        datum.add("hello there");
        datum.add("me second");
        datum.add("third");

        ListView listview1 = (ListView  )findViewById(R.id.listView1);

        ArrayAdapter<String> arrAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datum    );
        listview1.setAdapter(arrAdapter);

        ///on click event for list view


        ///default ui stuff
        //------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
            }
        });

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
    }



}
