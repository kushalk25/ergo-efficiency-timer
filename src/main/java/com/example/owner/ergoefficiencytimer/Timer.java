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

    public Timer(Activity _activity) {
        this.activity = _activity;

        this.handler = new Handler();

        this.shortBreakLength =new Time(0, 0, 10);
        this.longBreakLength = new Time(0, 0, 20);
        this.sprintLength = new Time(0, 1, 10);
        this.longBreakFrequency = 3;
        this.longBreakCountDown = this.longBreakFrequency;
        this.inSprint = true;


        totalTime = new Time(0, 3, 30);

        this.currentTime = new Time(sprintLength.getHour(), sprintLength.getMinute(), sprintLength.getSecond());

        //this.overallTimeRemaining = this.calcOverallTimeRemaining();

        //this.currentTimeRemaining = this.sprintLength;


        /*
        this.runable = new Runnable(){
            @Override
            public void run() {
             //   Log.d("KUSH", "HELLO from the handler");

                if(overallTimeRemaining > 0 && timerOn){

                    if(currentTimeRemaining > 0){
                        EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                        timeText.setText(""+currentTimeRemaining);

                //        TextView state = (TextView) activity.findViewById(R.id.currentTimeView);
                //        state.setText(this.);

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
        */
    }

   // private int calcOverallTimeRemaining() {

    //    return (this.overallHour * 60 * 60);
   // }


  //  public void setOverallTimeRemaining(int timeRemaining) {
  //      this.overallTimeRemaining = timeRemaining;
  //  }

 //   public void setOverallTimeRemaining(long timeRemaining) {
 //       this.overallTimeRemaining = timeRemaining;
 //   }

    /*
    public void decramentCurrentTime(){
        if (this.currentSecond == 0){
            if(this.currentMinute == 0){
                if(this.currentHour == 0){
                    return;
                }

                this.currentHour--;
                this.currentMinute = 60;
            }else {
                this.currentMinute--;
            }

            this.currentSecond = 60;
        }else{
            this.currentSecond--;
        }
    }

    public void decramentOverallTime(){
        if (this.overallSecond == 0){
            if(this.overallMinute == 0){
                if(this.overallHour == 0){
                    return;
                }

                this.overallHour--;
                this.overallMinute = 60;
            }else {
                this.overallMinute--;
            }

            this.overallSecond = 60;
        }else{
            this.overallSecond--;
        }
    }

    public String getCurrentTime(){
        String time;
        int hour = this.currentHour;
        int min = this.currentMinute;
        if(min / 10 == 0){
            time = ""+hour+":0"+min;
        } else{
            time = ""+hour+":"+min;
        }

        int sec = this.currentSecond;
        if(sec / 10 == 0){
            time = time+":0"+sec;
        } else {
            time = time+":"+sec;
        }
        return time;
    }

    public String getOverallTime(){
        String time;
        int hour = this.overallHour;
        int min = this.overallMinute;
        if(min / 10 == 0){
            time = ""+hour+":0"+min;
        } else{
            time = ""+hour+":"+min;
        }

        int sec = this.overallSecond;
        if(sec / 10 == 0){
            time = time+":0"+sec;
        } else {
            time = time+":"+sec;
        }
        return time;
    }
    */

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

                        TextView currentTime = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTime.setText(that.currentTime.toString());
                  //      TextView overallTime = (TextView) activity.findViewById(R.id.overallTimeView);
                   //     overallTime.setText(that.getOverallTime());

                        handler.postDelayed(this, 1000);
                    } else if(!inSprint){
                        System.out.println("Sprint time!");

                        inSprint = !inSprint;
                        that.currentTime.setTime(that.sprintLength.getHour(), that.sprintLength.getMinute(), that.sprintLength.getSecond());

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Sprint");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#FF6666"));


                        TextView currentTime = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTime.setText(that.currentTime.toString());
  //                      EditText timeText = (EditText) activity.findViewById(R.id.timeText);
//                        timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(longBreakCountDown == 0){
                        System.out.println("long break time!!");

                        longBreakCountDown = longBreakFrequency;
                        that.currentTime.setTime(that.longBreakLength.getHour(), that.longBreakLength.getMinute(), that.longBreakLength.getSecond());

                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Long Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        TextView currentTime = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTime.setText(that.currentTime.toString());
                      //  EditText timeText = (EditText) activity.findViewById(R.id.timeText);
                    //    timeText.setText(""+currentTimeRemaining);
                        handler.postDelayed(this, 1000);
                    } else if(inSprint){
                        System.out.println("short break time!!!!");
                        inSprint = !inSprint;
                        longBreakCountDown--;
                        that.currentTime.setTime(that.shortBreakLength.getHour(), that.shortBreakLength.getMinute(), that.shortBreakLength.getSecond());


                        TextView state = (TextView) activity.findViewById(R.id.stateText);
                        state.setText("Short Break");
                        RelativeLayout root = (RelativeLayout) activity.findViewById(R.id.rootLayout);
                        root.setBackgroundColor(Color.parseColor("#B2B2FF"));

                        TextView currentTime = (TextView) activity.findViewById(R.id.currentTimeView);
                        currentTime.setText(that.currentTime.toString());
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

                    //           int time = Integer.parseInt(timeText.getText().toString());

                }
                that.totalTime.decrement();
              //  overallTimeRemaining -= 1;
            }


        };
        return runnable;
    }

    public void toggleTimer(boolean isChecked){
        if(isChecked){
            EditText timeText = (EditText) activity.findViewById(R.id.timeText);

            int time = Integer.parseInt(timeText.getText().toString());
       //     this.setOverallTimeRemaining(time);

            Toast.makeText(activity, "ON", Toast.LENGTH_SHORT).show();
            timerOn = true;

            handler.postDelayed(this.getRunable(), 1000);
        } else {
            Toast.makeText(activity, "OFF", Toast.LENGTH_SHORT).show();
            timerOn = false;
        }
    }
}
