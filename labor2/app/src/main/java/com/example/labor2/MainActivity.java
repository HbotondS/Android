package com.example.labor2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, this, year, month, day);
    }

    public void datePick(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ((TextView) this.findViewById(R.id.birthDayText)).setText(year + ". " + month + ". " + dayOfMonth + ".");
    }

    public void login(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userdata", Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putString(getString(R.string.name), ((EditText) findViewById(R.id.nameText)).getText().toString())
                .putString(getString(R.string.email), ((EditText) findViewById(R.id.emailText)).getText().toString())
                .putString(getString(R.string.password), ((EditText) findViewById(R.id.passwordText)).getText().toString())
                .putString(getString(R.string.birthDate), ((TextView) findViewById(R.id.birthDayText)).getText().toString())
                .apply();

        Intent hobbys = new Intent(this, NewHobby.class);
        startActivity(hobbys);
    }
}
