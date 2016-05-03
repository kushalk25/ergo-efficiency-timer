package com.example.owner.ergoefficiencytimer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Timer myTimer;
    EditPage editPage;
    boolean editing;

  //  boolean timerOn = false;
 //   long timeRemaining = 0;
 //   private Handler handler;
    private ToggleButton toggleButton;

/*
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            Log.d("KUSH", "HELLO from the handler");


            timeRemaining -= 1;
            if(timeRemaining > 0 && timerOn){
                EditText timeText = (EditText) findViewById(R.id.timeText);

                timeText.setText(""+timeRemaining);
                handler.postDelayed(this, 1000);

     //           int time = Integer.parseInt(timeText.getText().toString());

            }

        }
    };
    */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        toggleButton = (ToggleButton) findViewById(R.id.timerToggle);
        toggleButton.setOnCheckedChangeListener(this);

        myTimer = new Timer(this);

        editPage = new EditPage(this, myTimer);
        editing = false;

    }

    public void navigationButtonOnClick(View v) {
    //    Typeface limeLightTypeFace = Typeface.createFromAsset(getAssets(), "limelight.ttf");
        Button button = (Button) v;

        setContentView(R.layout.edit_page);
        
        editing = true;
        editPage.loadTimerValues();

    }


    @Override
    public void onBackPressed() {

        if(editing){
            editPage.saveValues();
        }

        setContentView(R.layout.activity_main);

        myTimer.displayTime();
        toggleButton = (ToggleButton) findViewById(R.id.timerToggle);
        toggleButton.setOnCheckedChangeListener(this);
//        myTimer.newHandler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        myTimer.toggleTimer(isChecked);

      /*
        if(isChecked){
            EditText timeText = (EditText) findViewById(R.id.timeText);

            int time = Integer.parseInt(timeText.getText().toString());
            myTimer.setTimeRemaining(time);

            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;

            handler.postDelayed(runnable, 1000);
        } else {
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
        }
        */
    }
}
