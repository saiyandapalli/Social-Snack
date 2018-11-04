package com.saiyandapalli.vroom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MealActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    final String[] MONTHS = new String[]{"January", "February", "March", "April",
                                         "May", "June", "July", "August", "September",
                                         "October", "November", "December"};
    final String[] DAYS = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    Button datePickBtn;
    Button submitBtn;
    TextView timeTxt;
    TextView ampmTxt;
    TextView dateTxt;
    TextView dayTxt;
    TextView prefs;

    int day, dayOfWeek, month, year, hour, minute;
    String ampm;
    String prefsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        /** Set up date/time text */
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        ampm = (calendar.get(Calendar.AM_PM) == 0) ? "AM" : "PM";

        timeTxt = (TextView) findViewById(R.id.timeTxt);
        dateTxt = (TextView) findViewById(R.id.dateTxt);
        dayTxt = (TextView) findViewById(R.id.dayTxt);
        ampmTxt = (TextView) findViewById(R.id.ampmTxt);

        String hourStr = (hour % 12 != 0) ? Integer.toString(hour % 12) : "12";
        String minStr = (minute < 10) ? "0" + Integer.toString(minute) : Integer.toString(minute);
        dateTxt.setText(MONTHS[month] + " " + day + ", " + year);
        dayTxt.setText(DAYS[dayOfWeek]);
        timeTxt.setText("@" + hourStr + ":" + minStr);
        ampmTxt.setText(ampm);

        /* Sets up date/time picker. */
        datePickBtn = (Button) findViewById(R.id.datePickButton);

        datePickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MealActivity.this, MealActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });

        /* Set up preferences text */
        prefs = (TextView) findViewById(R.id.prefTxt);
        prefs.setHint("Enter your preferences here!\n(i.e. cuisines, restaurants, locations)");

        /* Sets up next button */
        submitBtn = (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefsTxt = prefs.getText().toString();

                //Start group selection screen!
                openGroupSelectionActivity();
            }

            public void openGroupSelectionActivity() {
                Intent intent = new Intent(new Intent(MealActivity.this, SendToGroupActivity.class));
                intent.putExtra("YEAR", year);
                intent.putExtra("MONTH", month);
                intent.putExtra("DAY", day);
                intent.putExtra("DAY_OF_WEEK", dayOfWeek);
                intent.putExtra("HOUR", hour);
                intent.putExtra("MINUTE", minute);
                intent.putExtra("PREFS", prefsTxt);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i;
        month = i1;
        day = i2;

        Calendar myCalendar = new GregorianCalendar(year, month, day);
        dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;

        dateTxt.setText(MONTHS[month] + " " + day + ", " + year);
        dayTxt.setText(DAYS[dayOfWeek]);

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(MealActivity.this, MealActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hour = i;
        minute = i1;

        String hourStr = (hour % 12 != 0) ? Integer.toString(hour % 12) : "12";
        String minStr = (minute < 10) ? "0" + Integer.toString(minute) : Integer.toString(minute);
        timeTxt.setText("@" + hourStr + ":" + minStr);
        ampmTxt.setText(ampm);
    }

}
