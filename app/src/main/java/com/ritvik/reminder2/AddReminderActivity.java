package com.ritvik.reminder2;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddReminderActivity extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();
    EditText editTextDate,editTextTime,editTextTitle, editTextDesc;
    //EditText editTextTime;
    Button addReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        editTextDate =(EditText) findViewById(R.id.dateEditText);
        editTextTime=(EditText) findViewById(R.id.timeEditText);
        editTextTitle=(EditText) findViewById(R.id.titleEditText);
        editTextDesc=(EditText) findViewById(R.id.descriptionEditText);
        addReminder = findViewById(R.id.addReminderButton);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderModel reminderModel;
                try {
                    if (!(editTextTitle.getText().toString()=="" || editTextDate.getText().toString()=="" || editTextTime.getText().toString()=="")) {
                        reminderModel = new ReminderModel(editTextTitle.getText().toString(), myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), myCalendar.get(Calendar.DATE), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.YEAR), editTextDesc.getText().toString());
                        Toast.makeText(AddReminderActivity.this, reminderModel.toString(), Toast.LENGTH_SHORT).show();
                        DatabaseHelper databaseHelper = new DatabaseHelper(AddReminderActivity.this);
                        boolean success = databaseHelper.addOne(reminderModel);
                        Toast.makeText(AddReminderActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AddReminderActivity.this, "Could not add reminder", Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e){
                    Toast.makeText(AddReminderActivity.this, "Could not add reminder", Toast.LENGTH_SHORT).show();
                    //reminderModel = new ReminderModel("Title", 10, 10, 24, 02, 2024, "None");

                }


                //DatabaseHelper databaseHelper= new DatabaseHelper(this);
//                List<ReminderModel> everyone = databaseHelper.getAll();
//                Toast.makeText(AddReminderActivity.this,everyone.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddReminderActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimePickerDialog.OnTimeSetListener time =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
//                myCalendar.set(Calendar.HOUR, 3);
//                myCalendar.getTime();
//                myCalendar.set(Calendar.HOUR, 14);
//                myCalendar.getTime();
                myCalendar.set(Calendar.HOUR, hour);
                myCalendar.set(Calendar.MINUTE,minute);
                updateLabelTime();
            }
        };
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AddReminderActivity.this,time,myCalendar.get(Calendar.HOUR),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat="dd MMMM yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.UK);
        editTextDate.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void updateLabelTime(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.UK);
        editTextTime.setText(dateFormat.format(myCalendar.getTime()));

    }

}