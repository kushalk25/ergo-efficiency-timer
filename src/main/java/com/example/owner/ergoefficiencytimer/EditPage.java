package com.example.owner.ergoefficiencytimer;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by owner on 4/30/2016.
 */
public class EditPage {

    Timer timer;
    Activity activity;

    EditPage(Activity _activity, Timer t){
        this.timer = t;
        this.activity = _activity;

    }

    // load all the values from the timer to the edit page textboxes
    public void loadTimerValues(){
        Time totalTime = this.timer.getTotalTime();

        EditText totalTimeHoursView = (EditText) this.activity.findViewById(R.id.totalHours);
        totalTimeHoursView.setText(""+totalTime.getHour());
        EditText totalTimeMinutesView = (EditText) this.activity.findViewById(R.id.totalMinutes);
        totalTimeMinutesView.setText(""+totalTime.getMinute());
        EditText totalTimeSecondsView = (EditText) this.activity.findViewById(R.id.totalSeconds);
        totalTimeSecondsView.setText(""+totalTime.getSecond());

        Time sprintLength = this.timer.getSprintLength();

        EditText sprintLengthHoursView = (EditText) this.activity.findViewById(R.id.sprintHours);
        sprintLengthHoursView.setText(""+sprintLength.getHour());
        EditText sprintLengthMinutesView = (EditText) this.activity.findViewById(R.id.sprintMinutes);
        sprintLengthMinutesView.setText(""+sprintLength.getMinute());
        EditText sprintLengthSecondsView = (EditText) this.activity.findViewById(R.id.sprintSeconds);
        sprintLengthSecondsView.setText("" + sprintLength.getSecond());

        Time shortLength = this.timer.getShortBreakLength();

        EditText shortLengthHoursView = (EditText) this.activity.findViewById(R.id.shortHours);
        shortLengthHoursView.setText("" + shortLength.getHour());
        EditText shortLengthMinutesView = (EditText) this.activity.findViewById(R.id.shortMinutes);
        shortLengthMinutesView.setText(""+shortLength.getMinute());
        EditText shortLengthSecondsView = (EditText) this.activity.findViewById(R.id.shortSeconds);
        shortLengthSecondsView.setText("" + shortLength.getSecond());

        Time longLength = this.timer.getLongBreakLength();

        EditText longLengthHoursView = (EditText) this.activity.findViewById(R.id.longHours);
        longLengthHoursView.setText(""+longLength.getHour());
        EditText longLengthMinutesView = (EditText) this.activity.findViewById(R.id.longMinutes);
        longLengthMinutesView.setText(""+longLength.getMinute());
        EditText longLengthSecondsView = (EditText) this.activity.findViewById(R.id.longSeconds);
        longLengthSecondsView.setText(""+longLength.getSecond());

        int frequency = this.timer.getLongBreakFrequency();

        EditText frequencyView = (EditText) this.activity.findViewById(R.id.longFrequency);
        frequencyView.setText("" + frequency);
    }

    // save all the values
    public void saveValues(){
        EditText totalHoursView = (EditText) activity.findViewById(R.id.totalHours);
        int totalHours = Integer.parseInt(totalHoursView.getText().toString());
        EditText totalMinutesView = (EditText) activity.findViewById(R.id.totalMinutes);
        int totalMinutes = Integer.parseInt(totalMinutesView.getText().toString());
        EditText totalSecondsView = (EditText) activity.findViewById(R.id.totalSeconds);
        int totalSeconds = Integer.parseInt(totalSecondsView.getText().toString());
        this.timer.setTotalTime(new Time(totalHours, totalMinutes, totalSeconds));

        EditText sprintLengthHoursView = (EditText) activity.findViewById(R.id.sprintHours);
        int sprintHours = Integer.parseInt(sprintLengthHoursView.getText().toString());
        EditText sprintLengthMinutesView = (EditText) activity.findViewById(R.id.sprintMinutes);
        int sprintMinutes = Integer.parseInt(sprintLengthMinutesView.getText().toString());
        EditText sprintLengthSecondsView = (EditText) activity.findViewById(R.id.sprintSeconds);
        int sprintSeconds = Integer.parseInt(sprintLengthSecondsView.getText().toString());
        this.timer.setSprintLength(new Time(sprintHours, sprintMinutes, sprintSeconds));

        EditText shortLengthHoursView = (EditText) activity.findViewById(R.id.shortHours);
        int shortHours = Integer.parseInt(shortLengthHoursView.getText().toString());
        EditText shortLengthMinutesView = (EditText) activity.findViewById(R.id.shortMinutes);
        int shortMinutes = Integer.parseInt(shortLengthMinutesView.getText().toString());
        EditText shortLengthSecondsView = (EditText) activity.findViewById(R.id.shortSeconds);
        int shortSeconds = Integer.parseInt(shortLengthSecondsView.getText().toString());
        this.timer.setShortBreakLength(new Time(shortHours, shortMinutes, shortSeconds));

        EditText longLengthHoursView = (EditText) activity.findViewById(R.id.longHours);
        int longHours = Integer.parseInt(longLengthHoursView.getText().toString());
        EditText longLengthMinutesView = (EditText) activity.findViewById(R.id.longMinutes);
        int longMinutes = Integer.parseInt(longLengthMinutesView.getText().toString());
        EditText longLengthSecondsView = (EditText) activity.findViewById(R.id.longSeconds);
        int longSeconds = Integer.parseInt(longLengthSecondsView.getText().toString());
        this.timer.setLongBreakLength(new Time(longHours, longMinutes, longSeconds));

        EditText frequencyView = (EditText) activity.findViewById(R.id.longFrequency);
        int frequency = Integer.parseInt(frequencyView.getText().toString());
        this.timer.setLongBreakFrequency(frequency);
    }
}
