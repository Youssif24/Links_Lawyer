package com.saad.youssif.arabiclawyer.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewNote extends AppCompatActivity {

    EditText courtName,client,opponent,prev_sitting,decision,date;
    Button saveNoteBtn;
    DBHelper dbHelper;
    Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    String currentDateandTime;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        courtName=findViewById(R.id.new_note_court_name);
        client=findViewById(R.id.new_note_client);
        opponent=findViewById(R.id.new_note_opponent);
        prev_sitting=findViewById(R.id.new_note_prev_sitting);
        decision=findViewById(R.id.new_note_decision);
        saveNoteBtn=findViewById(R.id.save_newNote_btn);
        date=findViewById(R.id.new_note_date);
        dbHelper=new DBHelper(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(NewNote.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datePickerDialog,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        datePickerDialog=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                currentDateandTime=year+"-"+month+"-"+dayOfMonth;
                date.setText(currentDateandTime);

            }
        };

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dbHelper.insertNote(courtName.getText().toString().trim(),client.getText().toString().trim()
                        ,opponent.getText().toString().trim(),prev_sitting.getText().toString().trim()
                        ,decision.getText().toString().trim(),currentDateandTime))
                {
                    Toast.makeText(NewNote.this,"تم تسجيل البيانات",Toast.LENGTH_SHORT).show();
                    emptyFields();
                }
                else
                {
                    Toast.makeText(NewNote.this,"لم يتم تسجيل البيانات",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void emptyFields()
    {
        courtName.setText("");
        client.setText("");
        opponent.setText("");
        prev_sitting.setText("");
        decision.setText("");
    }
}
