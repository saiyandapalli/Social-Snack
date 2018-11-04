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

public class MealActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    final String[] MONTHS = new String[]{"Jan", "Feb", "Mar", "Apr",
                                         "May", "Jun", "Jul", "Aug", "Sep",
                                         "Oct", "Nov", "Dec"};

    Button datePickBtn;
    Button submitBtn;
    TextView timeTxt;
    TextView moTxt;
    TextView dayTxt;
    TextView prefs;

    int year, day, month, hour, minute;
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
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        ampm = (hour >= 12) ? "PM" : "AM";

        timeTxt = (TextView) findViewById(R.id.timeTxt);
        dayTxt = (TextView) findViewById(R.id.dayTxt);
        moTxt = (TextView) findViewById(R.id.moTxt);

        String hourStr = (hour % 12 != 0) ? Integer.toString(hour % 12) : "12";
        String minStr = (minute < 10) ? "0" + Integer.toString(minute) : Integer.toString(minute);
        moTxt.setText(MONTHS[month]);
        dayTxt.setText(Integer.toString(day));
        timeTxt.setText("@" + hourStr + ":" + minStr + " " + ampm);

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
                Intent intent = new Intent(new Intent(MealActivity.this, MapsActivity.class));
                intent.putExtra("MONTH", month);
                intent.putExtra("DAY", day);
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

        moTxt.setText(MONTHS[month]);
        dayTxt.setText(Integer.toString(day));

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
        ampm = (hour >= 12) ? "PM" : "AM";

        String hourStr = (hour % 12 != 0) ? Integer.toString(hour % 12) : "12";
        String minStr = (minute < 10) ? "0" + Integer.toString(minute) : Integer.toString(minute);
        timeTxt.setText("@" + hourStr + ":" + minStr + " " + ampm);
    }

}
