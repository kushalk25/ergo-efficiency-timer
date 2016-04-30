package com.example.owner.ergoefficiencytimer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by owner on 4/26/2016.
 */
public class Timer {
    boolean timerOn = false;
    Runnable runable;
    long currentTimeRemaining;
    long overallTimeRemaining;

    int shortBreakLength;
    int longBreakLength;
    int sprintLength;
    int longBreakFrequency;
    int longBreakCountDown;
    boolean inSprint;



    Handler handler;
    Activity activity;

    public Timer(Activity _activity) {
        this.activity = _activity;

        this.handler = new Handler();

        this.shortBreakLength = 5;
        this.longBreakLength = 10;
        this.sprintLength = 10;
        this.longBreakFrequency = 3;
        this.longBreakCountDown = this.longBreakFrequency;
        this.inSprint = true;

        this.currentTimeRemaining = this.sprintLength;


        this.runable = new Runnable(){
            @Override
            public void run() {
             //   Log.d("KUSH", "HELLO from the handler");



                if(overallTimeRemaining > 0 && timerOn){



                    if(currentTimeRemaining > 0){
                        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(!inSprint){
                        System.out.println("Sprint time!");

                        inSprint = !inSprint;
                        currentTimeRemaining = sprintLength;

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Sprint");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#FF6666"));


                        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(longBreakCountDown == 0){
                        System.out.println("long break time!!");

                        longBreakCountDown = longBreakFrequency;
                        currentTimeRemaining = longBreakLength;

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Long Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(inSprint){
                        System.out.println("short break time!!!!");
                        inSprint = !inSprint;
                        longBreakCountDown--;
                        currentTimeRemaining = shortBreakLength;

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Short Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else {
                        System.out.println("ERRRRRRROOOOOORRRRR!!!!");
                    }

                    currentTimeRemaining -= 1;

                    //           int time = Integer.parseInt(timeText.getText().toString());

                }
                overallTimeRemaining -= 1;
            }


        };
    }


    public void setOverallTimeRemaining(int timeRemaining) {
        this.overallTimeRemaining = timeRemaining;
    }

    public void setOverallTimeRemaining(long timeRemaining) {
        this.overallTimeRemaining = timeRemaining;
    }

    public Runnable getRunable() {
        return runable;
    }

    public void toggleTimer(boolean isChecked){
        if(isChecked){
            EditText timeText = (EditText) activity.findViewById(R.id.timeText);

            int time = Integer.parseInt(timeText.getText().toString());
            this.setOverallTimeRemaining(time);

            Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;

            handler.postDelayed(this.runable, 1000);
        } else {
            Toast.makeText(activity, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
        }
    }
}
