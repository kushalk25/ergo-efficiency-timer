package com.example.owner.ergoefficiencytimer;

/**
 * Created by owner on 4/30/2016.
 */
public class Time {
    int hour;
    int minute;
    int second;



    // constructor
    Time(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    // constructor with time given
    Time(int hour, int min, int second){
        this.hour = hour;
        this.minute = min;
        this.second = second;
    }

    Time(int seconds){

        this.hour = seconds / (60*60);
        seconds = seconds % (60*60);

        this.minute = seconds / 60;
        seconds = seconds % 60;

        this.second = seconds;
    }

    public void setTime(int hour, int min, int second){
        this.hour = hour;
        this.minute = min;
        this.second = second;
    }

    public void setTime(int seconds){
        this.hour = seconds / (60*60);
        seconds = seconds % (60*60);

        this.minute = seconds / 60;
        seconds = seconds % 60;

        this.second = seconds;    }

    public  boolean stillMoreTime(){
        return ((this.hour + this.minute + this.second) > 0);
    }

    public void decrement(){
        if (this.second == 0){
            if(this.minute == 0){
                if(this.hour == 0){
                    return;
                }

                this.hour--;
                this.minute = 59;
            }else {
                this.minute--;
            }

            this.second = 59;
        }else{
            this.second--;
        }
    }

    public String toString(){
        String time;
        int hour = this.hour;
        int min = this.minute;
        if(min / 10 == 0){
            time = ""+hour+":0"+min;
        } else{
            time = ""+hour+":"+min;
        }

        int sec = this.second;
        if(sec / 10 == 0){
            time = time+":0"+sec;
        } else {
            time = time+":"+sec;
        }
        return time;
    }

    public int timeInSeconds(){
        int total = this.second;
        total += this.minute * 60;
        total += this.hour * 60 * 60;
        return total;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
