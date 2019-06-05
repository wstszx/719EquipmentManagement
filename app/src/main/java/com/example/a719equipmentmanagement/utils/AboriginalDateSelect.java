package com.example.a719equipmentmanagement.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AboriginalDateSelect {
    private DateFormat dateFormat = DateFormat.getDateTimeInstance();
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private static AboriginalDateSelect aboriginalTimeSelect;

    public static AboriginalDateSelect getInstance() {
        if (aboriginalTimeSelect == null) {
            aboriginalTimeSelect = new AboriginalDateSelect();
        }
        return aboriginalTimeSelect;
    }

    public void showDateTime(Context context, final int position) {
        final TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                listener.setValue(position,dateFormat);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.MONTH, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                timePickerDialog.show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    public void setListener(SelectDateListener listener) {
        this.listener = listener;
    }

    private SelectDateListener listener;

    public interface SelectDateListener {
        void setValue(int position, DateFormat dateFormat);
    }


}