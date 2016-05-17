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

    // class attributes
    Timer myTimer;
    EditPage editPage;
    boolean editing;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toggle button will run and pause the app
        toggleButton = (ToggleButton) findViewById(R.id.timerToggle);
        toggleButton.setOnCheckedChangeListener(this);

        myTimer = new Timer(this);
        myTimer.displayTime();

        editPage = new EditPage(this, myTimer);
        editing = false;

    }

    // the navigation buttons just set the content view
    public void navigationButtonOnClick(View v) {
    //    Typeface limeLightTypeFace = Typeface.createFromAsset(getAssets(), "limelight.ttf");
        Button button = (Button) v;

        // editing is true when we move to the edit page (for now the only other page)
        editing = true;
        setContentView(R.layout.edit_page);
        editPage.loadTimerValues();
    }

    public void stopAlarm(View v){
        myTimer.stopAlarm();
    }


    @Override
    // override will help to easily navigate the different pages since everything
    // is under one activity
    public void onBackPressed() {

        // if we were are currently in the editing page editing is true
        if(editing){
            editPage.saveValues();
            editing = false;
        }

        setContentView(R.layout.activity_main);
        myTimer.reset();
        myTimer.displayTime();
        // have to reset the toggle button
        toggleButton = (ToggleButton) findViewById(R.id.timerToggle);
        toggleButton.setOnCheckedChangeListener(this);
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
    }
}
