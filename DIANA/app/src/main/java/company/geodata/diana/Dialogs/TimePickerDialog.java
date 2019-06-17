package company.geodata.diana.Dialogs;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by jcmate on 7/18/2017.
 */

public class TimePickerDialog extends DialogFragment implements android.app.TimePickerDialog.OnTimeSetListener{

    private int mHour, mMinute;
    public View v;
    public String previousTime;
    public Boolean isAM;

    public TimePickerDialog newInstance(View v, String previousTime){
        this.v = v;
        this.previousTime = previousTime;
        return this;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        EditText editText = (EditText) v;
        if (hour >= 12) {
            mHour = hour - 12;
            isAM = false;
        } else {
            mHour = hour;
            isAM = true;
        }
        editText.setText(((mHour == 0) ? "12" : mHour) + ":" + ((minute < 10) ? ((minute == 0) ? "00" : "0" + minute) : minute)  + " " + ((isAM) ? "AM" : "PM"));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (previousTime.length() > 0) {
            String[] prev = previousTime.split(":");
            mHour = Integer.parseInt(prev[0]);

            String[] ampm = prev[1].split(" ");
            mMinute = Integer.parseInt(ampm[0]);
            if (ampm[1].equals("PM")) mHour += 12;
            else {
                if (mHour == 12) mHour = 0;
            }

        } else {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
        }

        android.app.TimePickerDialog timePickerDialog = new
                android.app.TimePickerDialog(getActivity(), this, mHour, mMinute, false);
        return timePickerDialog;
    }
}
