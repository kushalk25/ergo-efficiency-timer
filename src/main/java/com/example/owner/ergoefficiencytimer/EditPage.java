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

    }
}
