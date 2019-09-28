package com.example.a719equipmentmanagement.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AboriginalDateSelect {
    private Calendar calendar = Calendar.getInstance();
    private static AboriginalDateSelect aboriginalTimeSelect;

    public static AboriginalDateSelect getInstance() {
        if (aboriginalTimeSelect == null) {
            aboriginalTimeSelect = new AboriginalDateSelect();
        }
        return aboriginalTimeSelect;
    }

    public void showDateTime(Context context, final int position) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            listener.setValue(position, String.valueOf(calendar.getTime()));
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            timePickerDialog.show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    public void showDate(Context context, final int position) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Date date = calendar.getTime();
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
            String dateStr = simpleDateFormat.format(date);
            listener.setValue(position, dateStr);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void setListener(SelectDateListener listener) {
        this.listener = listener;
    }

    private SelectDateListener listener;

    public interface SelectDateListener {
        void setValue(int position, String date);
    }


}