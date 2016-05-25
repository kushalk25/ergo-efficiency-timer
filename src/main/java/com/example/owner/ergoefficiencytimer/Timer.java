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

    // these variable deal with sound
    SoundPool soundPool;
    int bellId;
    boolean alarmOn;

    // time that is displayed
    Time currentTime;
    Time totalTime;

    // options of the timer
    Time shortBreakLength;
    Time longBreakLength;
    Time sprintLength;
    int longBreakFrequency;
    int longBreakCountDown;

    boolean inSprint;
    boolean showTime;

    Handler handler;
    Activity activity;
    boolean firstPlay;

    public Timer(Activity _activity) {
        this.activity = _activity;

        this.handler = new Handler();

        // default time, will make customizable defaults this later
        this.shortBreakLength = new Time(0, 0, 5);
        this.longBreakLength = new Time(0, 0, 20);
        this.sprintLength = new Time(0, 0, 5);
        this.longBreakFrequency = 3;
        this.longBreakCountDown = this.longBreakFrequency;
        this.inSprint = true;
        this.showTime = true;
        totalTime = new Time(0, 1, 00);
        this.currentTime = new Time(sprintLength.getHour(), sprintLength.getMinute(), sprintLength.getSecond());

        // set up alarm
        this.soundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        this.bellId = this.soundPool.load(activity, R.raw.bell, 1);
        this.alarmOn = false;
        this.firstPlay = true;

    }

    // this function returns a runnable that will handle the thread which controls the timer.
    public Runnable getRunable() {

        final Timer that = this;

        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                if(totalTime.stillMoreTime() && timerOn){

                    // stardard action
                    if(that.currentTime.stillMoreTime()){

                  //      TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                  //      currentTimeView.setText(that.currentTime.toString());
                  //      TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                 //       totalTimeView.setText(that.totalTime.toString());
                        if(that.showTime){
                            that.displayTime();
                        }

                        handler.postDelayed(this, 1000);

                    // switching to sprint stage
                    } else if(!inSprint){
                        System.out.println("Sprint time!");
                        that.playAlarm();

                        // TODO: make this it's own subroutine, or make a subroutine that handles all state changes
                        inSprint = !inSprint;

                        that.changeDisplay("Sprint");

                   //     that.currentTime.setTime(that.sprintLength.getHour(), that.sprintLength.getMinute(), that.sprintLength.getSecond());
                   //     TextView state = (TextView) activity.findViewById(R.id.stateText);
                   //     state.setText("Sprint");
                   //     RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                   //     root.setBackgroundColor(Color.parseColor("#Be0000"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());

                        handler.postDelayed(this, 1000);

                    // if it is time for a long break
                    } else if(inSprint && longBreakCountDown == 0){
                        System.out.println("long break time!!");

                        that.playAlarm();
                        // reset longBreakCountDown
                        longBreakCountDown = longBreakFrequency;

                        that.changeDisplay("Long Break");
                        //that.currentTime.setTime(that.longBreakLength.getHour(), that.longBreakLength.getMinute(), that.longBreakLength.getSecond());

                      //  TextView state = (TextView) activity.findViewById(R.id.stateText);
                     //   state.setText("Long Break");
                     //   RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                     //   root.setBackgroundColor(Color.parseColor("#004080"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());

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
                        root.setBackgroundColor(Color.parseColor("#004080"));

                        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTimeView.setText(that.currentTime.toString());
                        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
                        totalTimeView.setText(that.totalTime.toString());

                        handler.postDelayed(this, 1000);
                    } else {
                        System.out.println("ERRRRRRROOOOOORRRRR!!!!");
                    }

                    that.currentTime.decrement();
                    that.totalTime.decrement();

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
        // isChecked refers to the toggle button
        if(isChecked){

            Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;
            RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
            TextView state = (TextView) activity.findViewById(R.id.stateText);
            if(this.inSprint) {
                root.setBackgroundColor(Color.parseColor("#Be0000"));
                state.setText("Sprint");
            } else {
                root.setBackgroundColor(Color.parseColor("#004080"));
            }


            handler.postDelayed(this.getRunable(), 1000);
        } else {
            Toast.makeText(activity, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
            handler.removeCallbacks(this.getRunable());
        }
    }

    public void changeDisplay(String phase){
        this.currentTime.setTime(this.sprintLength.getHour(), this.sprintLength.getMinute(), this.sprintLength.getSecond());
        TextView state = (TextView) this.activity.findViewById(R.id.stateText);
        state.setText(phase);
        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
        if(phase.equals("Sprint")) {
            root.setBackgroundColor(Color.parseColor("#Be0000"));
        }
    }

    public void disableTimeDisplay(){
         this.showTime = false;
    }
    public void enableTimeDisplay(){
        this.showTime = true;
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

        /*
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
        */

        if(that.firstPlay ){
            that.soundPool.play(that.bellId, 1, 1, 1, -1, 1);
            that.alarmOn = true;
            that.firstPlay = false;
        } else if(!that.alarmOn) {
            that.soundPool.resume(that.bellId);
            that.alarmOn = true;
        }
    }

    public void stopAlarm() {
        if(this.alarmOn){
            this.soundPool.pause(this.bellId);
            this.alarmOn = false;
        }
    }

    public void reset(){
        // when we reset the timer, we want to start in sprint stage
        this.inSprint = true;
        this.currentTime.setTime(this.sprintLength.getHour(), this.sprintLength.getMinute(), this.sprintLength.getSecond());
        this.longBreakCountDown = this.longBreakFrequency;
        TextView currentTimeView = (TextView) activity.findViewById(R.id.currentTimeView);
        currentTimeView.setText(this.currentTime.toString());
        TextView totalTimeView = (TextView) activity.findViewById(R.id.totalTimeView);
        totalTimeView.setText(this.totalTime.toString());
    }
}
