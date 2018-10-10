package com.android.admin.uber_guard;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

//net stuff
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;




public class Act2Activity extends AppCompatActivity {

     class RetreiveHttpString extends AsyncTask<Void, Void, String> {
    private Exception exception;

    protected String doInBackground(Void... url){

        String result = "HI";
        result = httpGet();
        return result;

    }

    protected void onPostExecute( String result  ){
        Button b = (Button) findViewById(R.id.buttonHttp    );
        b.setText(result);

    }
}
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
        Button  httpButton = (Button)findViewById(R.id.buttonHttp);

        ArrayAdapter<String> arrAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datum    );
        listview1.setAdapter(arrAdapter);

        ///on click event for list view
        new RetreiveHttpString().execute();

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

        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 httpGet();
            }
        });

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
    }

    public String httpGet(     ){
        URL url;
        HttpURLConnection urlConnection = null;
        String result ="";
        try {
            url = new URL("http://www.leoambo.com/getip");
            urlConnection = (HttpURLConnection) url.openConnection();


            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data !=-1){
                char current = (char)data;
                result+=current;

                data=isw.read();
                System.out.print(current);

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }
        return result;
    }

}
