package com.example.app25;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAcitivity extends AppCompatActivity {

    EditText name_input, address_input, dob_input;
    Button add_button;

    //
    Calendar calendar;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acitivity);

        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);
        dob_input = findViewById(R.id.dob_input);


        //
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }

            private void updateCalendar(){
                String Format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);

                dob_input.setText(sdf.format(calendar.getTime()));
            }
        };
        dob_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(AddAcitivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //


        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                if ((name_input.getText().toString().equals(""))) {
                    Toast.makeText(AddAcitivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                }

                if ((address_input.getText().toString().equals(""))) {
                    Toast.makeText(AddAcitivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                }

                 if ((dob_input.getText().toString().equals(""))) {
                    Toast.makeText(AddAcitivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                }

                else {
//
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddAcitivity.this);
                    myDB.addUser(name_input.getText().toString().trim(),
                            address_input.getText().toString().trim(),
                            dob_input.getText().toString().trim());
                    Intent intent1 = new Intent(AddAcitivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}