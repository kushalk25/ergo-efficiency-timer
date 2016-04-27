package com.example.owner.ergoefficiencytimer;

import android.app.Activity;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by owner on 4/26/2016.
 */
public class Timer {
    boolean timerOn = false;
    Runnable runable;
    long timeRemaining;
    Handler handler;
    Activity activity;

    public Timer(Activity _activity) {
        this.activity = _activity;

        this.handler = new Handler();

        this.runable = new Runnable(){
            @Override
            public void run() {
             //   Log.d("KUSH", "HELLO from the handler");


                timeRemaining -= 1;
                if(timeRemaining > 0 && timerOn){
                    EditText timeText = (EditText) activity.findViewById(R.id.timeText);

                    timeText.setText(""+timeRemaining);
                    handler.postDelayed(this, 1000);

                    //           int time = Integer.parseInt(timeText.getText().toString());

                }

            }
        };
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public Runnable getRunable() {
        return runable;
    }

    public void toggleTimer(boolean isChecked){
        if(isChecked){
            EditText timeText = (EditText) activity.findViewById(R.id.timeText);

            int time = Integer.parseInt(timeText.getText().toString());
            this.setTimeRemaining(time);

            Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;

            handler.postDelayed(this.runable, 1000);
        } else {
            Toast.makeText(activity, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
        }
    }
}
