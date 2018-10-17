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


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


//REBEL SAFE WALK 1 

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

class SendHttpString extends AsyncTask<String, Void, String>{
    private Exception exception;

    protected String doInBackground(String... url){

        String result = "HI";

        return result;

    }

    protected void onPostExecute( String result  ){
        /*Button b = (Button) findViewById(R.id.buttonHttp    );
        b.setText(result);*/

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

    private void sendMessage(final String msg) {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Socket s = new Socket("xxx.xxx.xxx.xxx", 9002);

                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //do sometihng with st
                            /*
                            String s = mTextViewReplyFromServer.getText().toString();
                            if (st.trim().length() != 0)
                                mTextViewReplyFromServer.setText(s + "\nFrom Server : " + st);*/
                        }
                    });

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }



}
