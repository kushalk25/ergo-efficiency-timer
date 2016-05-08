package com.example.owner.ergoefficiencytimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
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
  //  Runnable runable;

    /*
    long currentTimeRemaining;
    long overallTimeRemaining;

    int overallHour;
    int currentHour;
    int overallMinute;
    int currentMinute;
    int overallSecond;
    int currentSecond;
    */
    SoundPool soundPool;
    int bellId;
    boolean alarmOn;

    Time currentTime;
    Time totalTime;

    Time shortBreakLength;
    Time longBreakLength;
    Time sprintLength;
    int longBreakFrequency;
    int longBreakCountDown;

    boolean inSprint;

    Handler handler;
    Activity activity;
    boolean firstPlay;

    public Timer(Activity _activity) {
        this.activity = _activity;

        this.handler = new Handler();

        this.shortBreakLength = new Time(0, 0, 5);
        this.longBreakLength = new Time(0, 0, 20);
        this.sprintLength = new Time(0, 0, 5);
        this.longBreakFrequency = 3;
        this.longBreakCountDown = this.longBreakFrequency;
        this.inSprint = true;


        totalTime = new Time(0, 1, 00);

        this.currentTime = new Time(sprintLength.getHour(), sprintLength.getMinute(), sprintLength.getSecond());


        this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        this.bellId = this.soundPool.load(activity, R.raw.bell, 1);
        this.alarmOn = false;
        this.firstPlay = true;

    }

    public Runnable getRunable() {

        final Timer that = this;

        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                //   Log.d("KUSH", "HELLO from the handler");

                if(totalTime.stillMoreTime() && timerOn){

                    if(that.currentTime.stillMoreTime()){
                //        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                  //      timeText.setText(""+currentTimeRemaining);

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());

                        handler.postDelayed(this, 1000);
                    } else if(!inSprint){
                        System.out.println("Sprint time!");

                        that.playAlarm();

                        inSprint = !inSprint;
                        that.currentTime.setTime(that.sprintLength.getHour(), that.sprintLength.getMinute(), that.sprintLength.getSecond());

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Sprint");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#FF6666"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());
                        //EditText timeText = (EditText) activity.findViewById(R.id.timeText);
//                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(longBreakCountDown == 0){
                        System.out.println("long break time!!");

                        that.playAlarm();

                        longBreakCountDown = longBreakFrequency;
                        that.currentTime.setTime(that.longBreakLength.getHour(), that.longBreakLength.getMinute(), that.longBreakLength.getSecond());

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Long Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());
                      //  EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                    //    timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(inSprint){
                        System.out.println("short break time!!!!");

                        that.playAlarm();

                        inSprint = !inSprint;
                        longBreakCountDown--;
                        that.currentTime.setTime(that.shortBreakLength.getHour(), that.shortBreakLength.getMinute(), that.shortBreakLength.getSecond());


                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Short Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());
                        //TextView overallTime = (TextView) activity.findViewById(R.id.overallTimeView);
                        //overallTime.setText(that.getOverallTime());
                    //    EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                      //  timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else {
                        System.out.println("ERRRRRRROOOOOORRRRR!!!!");
                    }

               //     currentTimeRemaining -= 1;
                    that.currentTime.decrement();
                    that.totalTime.decrement();
                    //           int time = Integer.parseInt(timeText.getText().toString());

                } else {
                    // this else statement handles the final case
                    that.currentTime.decrement();
                    that.totalTime.decrement();
                    TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                    currentTimeView.setText(that.currentTime.toString());
                    TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                    totalTimeView.setText(that.totalTime.toString());
              //      handler.removeCallbacks(this);
                }

              //  overallTimeRemaining -= 1;
            }


        };
        return runnable;
    }

    public void newHandler(){
        this.handler = new Handler();
    }

    public void displayTime(){
        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
        currentTimeView.setText(this.currentTime.toString());
        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
        totalTimeView.setText(this.totalTime.toString());
    }

    public void toggleTimer(boolean isChecked){
        if(isChecked){
  //          EditText timeText = (EditText) activity.findViewById(R.id.timeText);
//
         //   int time = Integer.parseInt(timeText.getText().toString());
       //     this.setOverallTimeRemaining(time);

            Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;

            RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
            root.setBackgroundColor(Color.parseColor("#FF6666"));

            handler.postDelayed(this.getRunable(), 1000);
        } else {
            Toast.makeText(activity, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
            handler.removeCallbacks(this.getRunable());
        }
    }

    public Time getTotalTime() {
        return this.totalTime;
    }

    public Time getCurrentTime() {
        return this.currentTime;
    }

    public Time getSprintLength() {
        return sprintLength;
    }

    public Time getLongBreakLength() {
        return longBreakLength;
    }

    public Time getShortBreakLength() {
        return shortBreakLength;
    }

    public int getLongBreakFrequency() {
        return longBreakFrequency;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    public void setShortBreakLength(Time shortBreakLength) {
        this.shortBreakLength = shortBreakLength;
    }

    public void setLongBreakLength(Time longBreakLength) {
        this.longBreakLength = longBreakLength;
    }

    public void setSprintLength(Time sprintLength) {
        this.sprintLength = sprintLength;
    }

    public void setLongBreakFrequency(int longBreakFrequency) {
        this.longBreakFrequency = longBreakFrequency;
    }

    public void playAlarm(){

        final Timer that = this;

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        alert.setMessage("ALARM IS ON")
                .setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        that.stopAlarm();
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();

        if(this.firstPlay ){
            this.soundPool.play(this.bellId, 1, 1, 1, -1, 1);
            this.alarmOn = true;
            this.firstPlay = false;
        } else if(!this.alarmOn) {
            this.soundPool.resume(this.bellId);
            this.alarmOn = true;
        }
    }

    public void stopAlarm() {
        if(this.alarmOn){
            this.soundPool.pause(this.bellId);
            this.alarmOn = false;
        }
    }
}
