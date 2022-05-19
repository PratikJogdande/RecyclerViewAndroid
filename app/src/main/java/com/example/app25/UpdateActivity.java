package com.example.app25;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, address_input, dob_input;
    Button update_button, delete_button;

    String id, name, address, dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        address_input = findViewById(R.id.address_input2);
        dob_input = findViewById(R.id.dob_input2);

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

                new DatePickerDialog(UpdateActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //




        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        //First we call this
        getAndSetIntentData();

        //Set Actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if( ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if ((name_input.getText().toString().equals(""))) {
                        Toast.makeText(UpdateActivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                    }

                    else if ((address_input.getText().toString().equals(""))) {
                        Toast.makeText(UpdateActivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                    }

                    else if ((dob_input.getText().toString().equals(""))) {
                        Toast.makeText(UpdateActivity.this, "No empty field allowed..", Toast.LENGTH_SHORT).show();
                    }

                    else
                        {
                        //And only then we call this
                        MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                        name = name_input.getText().toString().trim();
                        address = address_input.getText().toString().trim();
                        dob = dob_input.getText().toString().trim();

                        myDB.updateData(id, name, address, dob);

                        Intent intent2 = new Intent(UpdateActivity.this, MainActivity.class);
                        startActivity(intent2);
                    }

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
        getIntent().hasExtra("address") && getIntent().hasExtra("dob")){
           //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            dob = getIntent().getStringExtra("dob");

            //Setting Intent Data
            name_input.setText(name);
            address_input.setText(address);
            dob_input.setText(dob);

        }else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}