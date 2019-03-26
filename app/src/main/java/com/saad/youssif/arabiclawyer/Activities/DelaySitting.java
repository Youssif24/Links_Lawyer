package com.saad.youssif.arabiclawyer.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DelaySitting extends AppCompatActivity {

    ListView listView;
    Button delayBtn;
    List<String> delayList;
    DBHelper dbHelper;
    Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePickerDialog;
    String date=null,d_date="",id;




    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_sitting);
        dbHelper=new DBHelper(this);
        listView=findViewById(R.id.delayListView);
        delayBtn=findViewById(R.id.delay_btn);

        Intent intent=getIntent();
        d_date=intent.getExtras().getString("d_date");
        id= intent.getExtras().getString("id");

        delayList=new ArrayList<>();
        delayList.add(d_date);
        delayList.addAll(dbHelper.getDelaySitting(id));


        // Creating adapter for listview
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.delaylistviewitems, delayList);
        // attaching data adapter to spinner
        listView.setAdapter(dataAdapter);


        delayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(DelaySitting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datePickerDialog,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        datePickerDialog=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                date=year+"-"+month+"-"+dayOfMonth;
                if(dbHelper.insertDelaySitting(id,date))
                {
                    delayList.add(date);
                    dataAdapter.notifyDataSetChanged();
                    Toast.makeText(DelaySitting.this,"تم تأجيل الجلسة",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(DelaySitting.this,"خطأ.... لم يتم تأجيل الجلسة",Toast.LENGTH_LONG).show();

                }

                //delay_date.setText(sittingDate);

            }
        };
    }

}
