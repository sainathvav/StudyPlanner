package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    Button eventDateBtn, eventTimeBtn, addEventBtn;
    EditText eventTitle, eventDescription;
    TextView eventDate, eventTime;
    String type, dateVal, timeVal;
    int Year, Month, Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Bundle extras = getIntent().getExtras();

        type = "UNKNOWN";
        if (extras != null) {
            if (extras.containsKey("type")) {
                type = extras.getString("type");
            }
        }

        eventTitle = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        eventDate = findViewById(R.id.event_date);
        eventTime = findViewById(R.id.event_time);

        eventDateBtn = findViewById(R.id.event_date_btn);
        eventTimeBtn = findViewById(R.id.event_time_btn);
        addEventBtn = findViewById(R.id.add_event_btn);

        eventDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                Year = c.get(Calendar.YEAR);
                Month = c.get(Calendar.MONTH);
                Day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String month = Integer.toString(monthOfYear+1);
                                if (month.length() == 1) {
                                    month = "0" + month;
                                }
                                String day = Integer.toString(dayOfMonth);
                                if (day.length() == 1) {
                                    day = "0" + day;
                                }
                                dateVal = day + "/" + month + "/" + year;
                                eventDate.setText(dateVal);

                            }
                        }, Year, Month, Day);
                datePickerDialog.show();


            }
        });

        eventTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String type = "AM";
                        if (selectedHour == 0) {
                            selectedHour = 12;
                        }
                        else if (selectedHour == 12) {
                            type = "PM";
                        }
                        else {
                            selectedHour = selectedHour%12;
                            type = "PM";
                        }
                        String hour = Integer.toString(selectedHour);
                        String minute = Integer.toString(selectedMinute);
                        if (minute.length() == 1) {
                            minute = "0"+minute;
                        }
                        eventTime.setText( selectedHour + ":" + minute + " " + type);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddEventActivity.this);

                boolean result = dataBaseHelper.addEvent(new Event(type, eventTitle.getText().toString(),
                        eventDate.getText().toString(), eventTime.getText().toString(), eventDescription.getText().toString()));
                if (result) {
                    Toast.makeText(AddEventActivity.this, "Successfully added a new event", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(AddEventActivity.this, "Failed to add new event", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(AddEventActivity.this, LeftSlideBarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}