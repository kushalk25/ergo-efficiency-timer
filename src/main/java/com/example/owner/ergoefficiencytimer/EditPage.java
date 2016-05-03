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
        sprintLengthSecondsView.setText(""+sprintLength.getSecond());

    }

    public void saveValues(){
        EditText totalHoursView = (EditText) activity.findViewById(R.id.totalHours);
        int totalHours = Integer.parseInt(totalHoursView.getText().toString());
        EditText totalMinutesView = (EditText) activity.findViewById(R.id.totalMinutes);
        int totalMinutes = Integer.parseInt(totalMinutesView.getText().toString());
        EditText totalSecondsView = (EditText) activity.findViewById(R.id.totalSeconds);
        int totalSeconds = Integer.parseInt(totalSecondsView.getText().toString());
        this.timer.setTotalTime(new Time(totalHours, totalMinutes, totalSeconds));

    }
}
